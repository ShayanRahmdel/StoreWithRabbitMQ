package com.example.product.controller;


import com.example.product.model.Product;
import com.example.product.model.dto.ProductResponse;
import com.example.product.service.ProductService;
import com.example.product.service.producer.ProductProducerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductProducerService producerService;
    private final ProductService productService;

    public ProductController(ProductProducerService producerService, ProductService productService) {
        this.producerService = producerService;
        this.productService = productService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createOrUpdate(product), HttpStatus.CREATED);

    }

    @GetMapping("/load-by-id")
    public ResponseEntity<ProductResponse> loadById(@RequestParam Integer id) {
        return new ResponseEntity<>(productService.loadById(id), HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam Integer id) {
        productService.deleteById(id);
        System.out.println("success delete");
    }

    @GetMapping("/send-all-products")
    public void sendAllProducts() {
        producerService.sendAllProduct(productService.loadAll());
    }
}
