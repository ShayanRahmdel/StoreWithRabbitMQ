package com.example.product.service.impl;

import com.example.product.mapper.ProductMapper;
import com.example.product.model.Product;
import com.example.product.model.dto.ProductResponse;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;


    @Override
    public ProductResponse createOrUpdate(Product product) {
        repository.save(product);
        return ProductMapper.INSTANCE.modelToResponse(product);
    }

    @Override
    public ProductResponse loadById(Integer id) {
        return ProductMapper.INSTANCE.modelToResponse(repository.findById(id).orElseThrow(()
                -> new NullPointerException("Couldn't find product'")));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductResponse> loadAll() {
        List<Product> products = repository.findAll();
        return ProductMapper.INSTANCE.listModelToResponse(products);
    }
}
