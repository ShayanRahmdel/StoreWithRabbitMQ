package com.example.basket.model.dto;

import com.example.basket.model.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketResponse {

    private Status status;


    private Double discount;


    private Double totalPrice;
}
