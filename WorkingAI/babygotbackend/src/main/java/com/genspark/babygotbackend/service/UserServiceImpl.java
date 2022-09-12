package com.genspark.babygotbackend.service;

import com.genspark.babygotbackend.entity.Group;
import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.exception.BadRequestException;
import com.genspark.babygotbackend.payload.UserResponse;
import com.genspark.babygotbackend.repository.GroupRepository;
import com.genspark.babygotbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    @Transactional
    public UserResponse addUser(User user) {

        Optional<User> o = userRepository.findUserByEmail(user.getEmail());

        if (o.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        //encrypt password Bcrypt
        user.setPassword(encoder.encode(user.getPassword()));

        //first user registered granted admin role
        if (userRepository.count() == 0) {

            user.addUserGroups(groupRepository.findByCode("administrator"));

        } else {

            //role user granted after admin registration
            user.addUserGroups(groupRepository.findByCode("user"));
        }

        user.setEmail(user.getEmail());
        user.setEnabled(true);

        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                getRoles(user.getUserGroups())
        );
    }

    //return List of roles sorted ascending by name
    private List<String> getRoles(Set<Group> groups) {

        List<String> roles = new ArrayList<>();

        for (Group g : groups) {
            roles.add(g.getName());
        }

        Collections.sort(roles);

        return roles;
    }
}
