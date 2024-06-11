package com.example.order.service;


import com.example.order.model.dto.OrderRequest;
import com.example.order.model.dto.OrderResponse;

import java.util.List;


public interface OrderService {

    List<OrderResponse> create(OrderRequest orderRequest);

    void submitOrder(Integer id);

    OrderResponse  Update(OrderRequest orderRequest);

    OrderResponse findById(Integer id);

    void deleteOrder(Integer id);





}
