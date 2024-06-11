package com.example.basket.service.impl;

import com.example.basket.config.DiscountFeign;
import com.example.basket.mapper.BasketMapper;
import com.example.basket.model.Basket;
import com.example.basket.model.OrderResponse;
import com.example.basket.model.Status;
import com.example.basket.model.dto.BasketResponse;
import com.example.basket.repository.BasketRepository;
import com.example.basket.service.BasketService;
import com.example.basket.service.concumer.OrderResponseConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    BasketRepository repository;
    OrderResponseConsumer orderResponseConsumer;
    DiscountFeign discountFeign;

    @Override
    public BasketResponse createOrUpdate() throws JsonProcessingException {
        List<OrderResponse> orderResponses = orderResponseConsumer.receiveOrderResponse();
        Basket basket = orderResponses.stream()
                .map(OrderResponse::getBasket)
                .findFirst().orElseThrow(() -> new NullPointerException("No order response"));
        basket.setOrderResponses(orderResponses);
        Double totalPrice = setTotalPrice(basket);
        basket.setTotalPrice(totalPrice);
        if (basket.getTotalPrice() > 1000000.0) {
            basket.setDiscount(discountFeign.getFirstDiscount());
        }
        if (basket.getTotalPrice() > 2000000.0) {
            basket.setDiscount(discountFeign.getSecondDiscount());
        }
        if (basket.getTotalPrice() > 3000000.0) {
            basket.setDiscount(discountFeign.getThirdDiscount());
        }
        repository.save(basket);
        return BasketMapper.INSTANCE.modelToResponse(basket);
    }

    @Override
    public BasketResponse loadById(Integer id) {
        return BasketMapper.INSTANCE.modelToResponse(repository.findById(id).orElseThrow(() ->
                new NullPointerException("Could not find id")));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void changeStatus(Integer id) {
        Basket basket = repository.findById(id).orElseThrow(() -> new NullPointerException("Could not find id"));
        basket.setStatus(Status.PAYED);
        repository.save(basket);
    }

    @Override
    public Double setTotalPrice(Basket basket) {
        if (basket != null) {
            double totalPrice = 0.0;
            List<OrderResponse> orderResponses = basket.getOrderResponses();
            for (OrderResponse orders : orderResponses) {
                totalPrice = orders.getPrice() * orders.getQuantity() + totalPrice;
            }
            return totalPrice;
        }
        return null;
    }
}
