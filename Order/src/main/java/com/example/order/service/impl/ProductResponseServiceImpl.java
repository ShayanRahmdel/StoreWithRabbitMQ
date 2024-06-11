package com.example.order.service.impl;

import com.example.order.model.dto.ProductResponse;
import com.example.order.repostory.ProductResponseRepo;
import com.example.order.service.ProductResponseService;
import com.example.order.service.cuncumer.ProductConcumerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductResponseServiceImpl implements ProductResponseService {

    ProductResponseRepo responseRepo;
    ProductConcumerService productConcumerService;

    @Override
    public List<ProductResponse> getProducts() {
        productConcumerService.getProducts();
        return responseRepo.findAll();
    }

    @Override
    public ProductResponse loadById(Integer id) {
        return responseRepo.findById(id).orElseThrow(() -> new NullPointerException("No such product"));
    }
}
