package com.example.users.mapper;

import com.example.users.model.User;
import com.example.users.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserResponse modelToResponse(User user);
}
