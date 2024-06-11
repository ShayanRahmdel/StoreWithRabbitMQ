package com.example.users.service.producer;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j

public class UserProducerService {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProducerService.class);

    public void sendUserId(String userId){
//        UserDto userDto = UserDtoMapper.INSTANCE.modelToDto(user);
        LOGGER.info("Sending user dto " + userId);
        rabbitTemplate.convertAndSend("store-exchange", "user-routing-key", userId);
        LOGGER.info("Sent user dto " + userId);
    }

}
