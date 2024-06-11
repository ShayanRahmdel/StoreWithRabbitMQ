package com.example.basket.mapper;

import com.example.basket.model.Basket;
import com.example.basket.model.dto.BasketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);


    BasketResponse modelToResponse(Basket basket);

}
