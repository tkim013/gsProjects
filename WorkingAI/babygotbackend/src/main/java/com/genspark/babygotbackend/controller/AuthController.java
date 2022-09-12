package com.genspark.babygotbackend.controller;

import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.payload.request.NewPassword;
import com.genspark.babygotbackend.payload.response.ChangePasswordResponse;
import com.genspark.babygotbackend.payload.response.UserResponse;
import com.genspark.babygotbackend.security.UserDetailsImpl;
import com.genspark.babygotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/register")
    UserResponse registerUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @PostMapping("/auth/changepwd")
    @Secured({ "ROLE_USER", "ROLE_ADMINISTRATOR" })
    ChangePasswordResponse changePassword(
            @RequestBody NewPassword newpassword,
            @AuthenticationPrincipal UserDetailsImpl details
    ) {
        return this.userService.changePassword(newpassword, details);
    }
}
