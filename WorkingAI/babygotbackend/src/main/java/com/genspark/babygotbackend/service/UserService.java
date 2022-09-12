package com.genspark.babygotbackend.service;

import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.payload.request.NewPassword;
import com.genspark.babygotbackend.payload.response.ChangePasswordResponse;
import com.genspark.babygotbackend.payload.response.DeleteUserResponse;
import com.genspark.babygotbackend.payload.response.UserResponse;
import com.genspark.babygotbackend.security.UserDetailsImpl;

import java.util.List;

public interface UserService {

    UserResponse addUser(User user);

    ChangePasswordResponse changePassword(NewPassword newpassword, UserDetailsImpl details);

    List<UserResponse> getAllUsers();

    DeleteUserResponse deleteUserByEmail(String email, UserDetailsImpl details);
}
