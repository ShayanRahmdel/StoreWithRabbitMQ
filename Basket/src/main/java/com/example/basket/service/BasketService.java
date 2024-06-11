package com.example.basket.service;

import com.example.basket.model.Basket;
import com.example.basket.model.dto.BasketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BasketService {

    BasketResponse createOrUpdate() throws JsonProcessingException;

    BasketResponse loadById(Integer id);

    void delete(Integer id);

    void changeStatus(Integer id);


    Double setTotalPrice(Basket basket);
}
