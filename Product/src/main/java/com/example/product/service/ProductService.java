package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.dto.ProductResponse;


import java.util.List;

public interface ProductService {

    ProductResponse createOrUpdate(Product product);

    ProductResponse loadById(Integer id);

    void deleteById(Integer id);


    List<ProductResponse> loadAll();

}
