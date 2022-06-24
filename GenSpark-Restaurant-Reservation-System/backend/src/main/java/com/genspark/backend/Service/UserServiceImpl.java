package com.genspark.backend.Service;

import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        Optional<User> a = this.userRepository.findById(id);

        User user;

        if (a.isPresent())
        {
            user = a.get();
        } else {
            throw new RuntimeException("Account not found for id : " + id);
        }
        return user;
    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        this.userRepository.save(user);
        return ResponseEntity.ok("valid");
    }

    @Override
    public User updateUser(User user, Long userID) {

        Optional<User> o = this.userRepository.findById(userID);

        if(!o.isPresent())
        {
            throw new RuntimeException("user with id: " + userID + " not found");
        }

        User userUpdated = o.get();

        if(user.getUsername() != null) {
            userUpdated.setUsername(user.getUsername());
        }
        if(user.getPassword() != null) {
            userUpdated.setPassword(user.getPassword());
        }
        if(user.getEmail() != null) {
            userUpdated.setEmail(user.getEmail());
        }

        return this.userRepository.save(userUpdated);
    }

    @Override
    public String deleteUserById(Long id) {

        this.userRepository.deleteById(id);

        return "Deleted Successfully";
    }

    @Override
    public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
