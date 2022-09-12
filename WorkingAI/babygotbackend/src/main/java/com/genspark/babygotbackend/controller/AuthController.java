package com.genspark.babygotbackend.controller;

import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.payload.UserResponse;
import com.genspark.babygotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userservice;

    @PostMapping("/register")
    UserResponse registerUser(@RequestBody User user) {

        return userservice.addUser(user);
    }
}
