package com.example.discount.controller;


import com.example.discount.model.Discount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final Discount discount;

    public DiscountController(Discount discount) {
        this.discount = discount;
    }

    @GetMapping("/send-first-discount")
    public Double getFirstDiscount(){
        return discount.getFirstDiscount();
    }

    @GetMapping("/send-second-discount")
    public Double getSecondDiscount(){
        return discount.getSecondDiscount();
    }
    @GetMapping("/send-third-discount")
    public Double getThirdDiscount(){
        return discount.getThirdDiscount();
    }


}
