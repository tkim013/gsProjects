package com.genspark.babygotbackend.controller;

import com.genspark.babygotbackend.payload.response.DeleteUserResponse;
import com.genspark.babygotbackend.payload.response.UserResponse;
import com.genspark.babygotbackend.security.UserDetailsImpl;
import com.genspark.babygotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Secured("ROLE_ADMINISTRATOR")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/user/{email}")
    DeleteUserResponse deleteUser(@PathVariable String email,
                                  @AuthenticationPrincipal UserDetailsImpl details) {

        return this.userService.deleteUserByEmail(email, details);
    }
}
