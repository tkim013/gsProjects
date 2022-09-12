package com.genspark.babygotbackend.service;

import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.payload.UserResponse;

public interface UserService {

    UserResponse addUser(User user);
}
