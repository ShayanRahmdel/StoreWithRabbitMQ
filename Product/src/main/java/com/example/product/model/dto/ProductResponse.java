package com.example.product.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProductResponse implements Serializable {

    private String name;

    private Double price;
}
