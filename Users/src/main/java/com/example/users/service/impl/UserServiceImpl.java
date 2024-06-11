package com.example.users.service.impl;



import com.example.users.mapper.UserMapper;
import com.example.users.model.User;
import com.example.users.model.dto.UserResponse;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserService;
import com.example.users.service.producer.UserProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Auditable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProducerService producer;

    private final UserRepository repository;



    @Override
    public UserResponse create(User user) {
        User createdUser = repository.save(user);
        producer.sendUserId(createdUser.getId().toString());
        return UserMapper.INSTANCE.modelToResponse(createdUser);
    }

    @Override
    public UserResponse loadById(Integer id) {
        return UserMapper.INSTANCE.modelToResponse(repository.findById(id).orElseThrow(()
                -> new NullPointerException("Could not find this id")));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
