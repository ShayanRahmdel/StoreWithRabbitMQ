package com.example.order.repostory;

import com.example.order.model.dto.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductResponseRepo extends JpaRepository<ProductResponse,Integer> {
}
