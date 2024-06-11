package com.example.order.service.impl;

import com.example.order.mapper.OrderMapper;
import com.example.order.model.Order;
import com.example.order.model.dto.OrderRequest;
import com.example.order.model.dto.OrderResponse;
import com.example.order.model.dto.ProductResponse;
import com.example.order.repostory.OrderRepository;
import com.example.order.service.OrderService;
import com.example.order.service.ProductResponseService;
import com.example.order.service.cuncumer.UserConsumerService;
import com.example.order.service.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final UserConsumerService userConsumerService;
    private final ProductResponseService productResponseService;
    private final OrderProducer producer;



    @Override
    public List<OrderResponse> create(OrderRequest orderRequest) {
        userConsumerService.receiveUserId();
        ProductResponse productResponse = productResponseService.loadById(orderRequest.getProductId());
        Order order = OrderMapper.INSTANCE.reqToModel(orderRequest);
        order.setPrice(productResponse.getPrice());
        repository.save(order);
        OrderResponse orderResponse = OrderMapper.INSTANCE.modelToResponse(order);
        List<OrderResponse> orders = new ArrayList<>();
        orders.add(orderResponse);
        return orders;

    }

    @Override
    public void submitOrder(Integer id) {
        List<Order> orderByUserId = repository.findOrderByUserId(id);
        for (Order order : orderByUserId){
            order.setIsSend(true);
            repository.save(order);
        }
        List<OrderResponse> orderResponses = OrderMapper.INSTANCE.listModelToResponse(orderByUserId);

        producer.sendOrderResponse(orderResponses);
    }

    @Override
    public OrderResponse Update(OrderRequest orderRequest) {
//        userConsumerService.receiveUserId();
//        try {
//            Order order = repository.findOrderByUserId(orderRequest.getUserId());
//                order.setQuantity(orderRequest.getQuantity());
//                order.setProductId(orderRequest.getProductId());
//                ProductResponse productResponse = productResponseService.loadById(orderRequest.getProductId());
//                order.setPrice(productResponse.getPrice());
//                repository.save(order);
//            OrderResponse orderResponse = OrderMapper.INSTANCE.modelToResponse(order);
////            producer.sendOrderResponse(orderResponse);
//            return orderResponse;
//
//        }catch (NullPointerException e){
//            System.out.println("not have this id");
//        }

        return null;
    }

    @Override
    public OrderResponse findById(Integer id) {
        return OrderMapper.INSTANCE.modelToResponse(repository.findById(id).orElseThrow(() ->
                new NullPointerException("not find id")));
    }

    @Override
    public void deleteOrder(Integer id) {
        findById(id);
        repository.deleteById(id);
    }


}
