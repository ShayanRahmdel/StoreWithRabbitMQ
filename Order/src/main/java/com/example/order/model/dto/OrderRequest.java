package com.example.order.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

    private Integer userId;

    private Integer productId;

    private Integer quantity;


}
