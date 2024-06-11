package com.example.order.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderResponse {

    private Integer userId;

    private Integer productId;

    private Integer quantity;

    private Double price;

    private Boolean isSend;
}
