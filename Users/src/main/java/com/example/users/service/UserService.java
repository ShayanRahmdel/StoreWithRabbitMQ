package com.example.users.service;

import com.example.users.model.User;
import com.example.users.model.dto.UserResponse;
import org.springframework.data.domain.Auditable;


public interface UserService {
    UserResponse create(User user);

    UserResponse loadById(Integer id);

    void delete(Integer id);
}
