package com.example.order.service.producer;


import com.example.order.model.dto.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class OrderProducer {
    RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    public void sendOrderResponse(List<OrderResponse> orderResponses){
        LOGGER.info("Order sending " + orderResponses );
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderResponseJson = objectMapper.writeValueAsString(orderResponses);

            rabbitTemplate.convertAndSend("store-exchange", "order-routing-key", orderResponseJson);

            LOGGER.info("Order sent " + orderResponses);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting OrderResponse to JSON: " + e.getMessage());
        }
    }
}
