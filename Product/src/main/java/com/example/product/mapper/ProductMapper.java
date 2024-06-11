package com.example.product.mapper;

import com.example.product.model.Product;
import com.example.product.model.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse modelToResponse(Product product);

    List<ProductResponse> listModelToResponse(List<Product> products);

}
