package com.example.basket.service.concumer;


import com.example.basket.model.Basket;
import com.example.basket.model.OrderResponse;
import com.example.basket.model.Status;
import com.example.basket.repository.BasketRepository;
import com.example.basket.repository.OrderResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderResponseConsumer {

    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper;
    OrderResponseRepository repository;
    BasketRepository basketRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResponseConsumer.class);

    public List<OrderResponse> receiveOrderResponse() throws JsonProcessingException {

        Message message = rabbitTemplate.receive("order-queue");

        assert message != null;
        String orderResponseJson = new String(message.getBody());
        TypeReference<List<OrderResponse>> typeReference = new TypeReference<>() {
        };
        List<OrderResponse> receivedOrderResponses = objectMapper.readValue(orderResponseJson, typeReference);
        repository.saveAll(receivedOrderResponses);
        Basket basket = new Basket();
        basket.setStatus(Status.WAITING_FOR_PAY);
        basketRepository.save(basket);
        basket.setOrderResponses(receivedOrderResponses);
        basketRepository.save(basket);
        for (OrderResponse orders : receivedOrderResponses) {
            orders.setBasket(basket);
            repository.save(orders);
        }

        LOGGER.info("Received order response: " + receivedOrderResponses);
        return receivedOrderResponses;
    }
}
