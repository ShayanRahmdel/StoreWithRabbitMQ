package com.example.discount.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class Discount {


    private final Double firstDiscount = 0.2;
    private final Double secondDiscount = 0.4;
    private final Double thirdDiscount = 0.6;

}
