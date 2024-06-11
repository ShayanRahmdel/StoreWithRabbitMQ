package com.example.order.service;

import com.example.order.model.dto.ProductResponse;

import java.util.List;

public interface ProductResponseService {


    List<ProductResponse> getProducts();

    ProductResponse loadById(Integer id);

}
