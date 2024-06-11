package com.example.order.service.cuncumer;


import com.example.order.model.Order;
import com.example.order.repostory.OrderRepository;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;


import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumerService {

    private final RabbitTemplate rabbitTemplate;
    private final OrderRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumerService.class);

    public void receiveUserId() {
        rabbitTemplate.setExchange("store-exchange");
        rabbitTemplate.setRoutingKey("user-routing-key");

        try {
            String userId = rabbitTemplate.receiveAndConvert("user-queue").toString();

            LOGGER.info("Received user id " + userId);
            Order order = new Order();
            order.setUserId(Integer.parseInt(userId));
            repository.save(order);
            LOGGER.info("Received user ID: {}", userId);

        } catch (NullPointerException e) {
            LOGGER.error("Failed to parse user ID: {}", e.getMessage());
        }
    }

}
