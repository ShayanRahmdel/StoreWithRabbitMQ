package com.example.order.mapper;

import com.example.order.model.Order;
import com.example.order.model.dto.OrderRequest;
import com.example.order.model.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order reqToModel(OrderRequest orderRequest);

    OrderResponse modelToResponse(Order order);

    List<OrderResponse> listModelToResponse(List<Order> orders);
}
