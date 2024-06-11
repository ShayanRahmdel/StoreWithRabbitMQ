package com.example.order.service.cuncumer;

import com.example.order.model.dto.ProductResponse;
import com.example.order.repostory.ProductResponseRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductConcumerService {
    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper;
    ProductResponseRepo responseRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumerService.class);

    public List<ProductResponse> getProducts()  {

            try {

                String productResponseBytes = Objects.requireNonNull(rabbitTemplate.receiveAndConvert("product-queue")).toString();
                List<ProductResponse> productResponseList = objectMapper.readValue(productResponseBytes, new TypeReference<>() {});
                LOGGER.info("Received all products: " + productResponseList);
                responseRepo.saveAll(productResponseList);

            } catch (Exception e) {
                LOGGER.error("Error processing all products: " + e.getMessage());
            }
            return null;
    }
}
