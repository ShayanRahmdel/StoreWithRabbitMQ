package com.example.basket.repository;

import com.example.basket.model.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderResponseRepository extends JpaRepository<OrderResponse,Integer> {
}
