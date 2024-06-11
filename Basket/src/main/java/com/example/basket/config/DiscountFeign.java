package com.example.basket.config;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "feign" ,url = "http://localhost:3030/api/v1/discount")
public interface DiscountFeign {

    @RequestMapping(value = "/send-first-discount",produces =  "application/json")
    Double getFirstDiscount();

    @RequestMapping(value = "/send-second-discount",produces = "application/json")
    Double getSecondDiscount();

    @RequestMapping(value = "/send-third-discount",produces = "application/json")
    Double getThirdDiscount();
}
