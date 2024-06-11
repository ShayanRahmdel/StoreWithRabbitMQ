package com.example.product.service.producer;

import com.example.product.model.dto.ProductResponse;
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
public class ProductProducerService {
    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProducerService.class);



    public void sendAllProduct(List<ProductResponse> productResponses) {
        try {
            String productResponsesJson = objectMapper.writeValueAsString(productResponses);

            LOGGER.info("Sending all products: " + productResponsesJson);
            rabbitTemplate.convertAndSend("product-queue", productResponsesJson);
            LOGGER.info("Sent all products: " + productResponsesJson);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting productResponses to JSON: " + e.getMessage());
        }
    }

}
