package com.example.users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    private String username;

    private String password;

    private String firstName;

    private String lastName;
}
