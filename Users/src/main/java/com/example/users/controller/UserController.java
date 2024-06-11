package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.model.dto.UserResponse;
import com.example.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }


    @GetMapping("/load")
    public ResponseEntity<UserResponse> loadById(@RequestParam Integer id){
        return new ResponseEntity<>(userService.loadById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Integer id){
        userService.delete(id);
        return new ResponseEntity<>("success deleted", HttpStatus.OK);
    }

}
