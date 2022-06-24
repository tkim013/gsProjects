package com.genspark.backend.Service;

import com.genspark.backend.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    ResponseEntity<String> addUser(User user);
    User updateUser(User user, Long userID);
    String deleteUserById(Long id);
    List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy);
}
