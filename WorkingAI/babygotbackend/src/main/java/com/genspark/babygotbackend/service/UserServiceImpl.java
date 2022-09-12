package com.genspark.babygotbackend.service;

import com.genspark.babygotbackend.entity.Group;
import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.exception.BadRequestException;
import com.genspark.babygotbackend.exception.NotFoundException;
import com.genspark.babygotbackend.payload.request.NewPassword;
import com.genspark.babygotbackend.payload.response.ChangePasswordResponse;
import com.genspark.babygotbackend.payload.response.DeleteUserResponse;
import com.genspark.babygotbackend.payload.response.UserResponse;
import com.genspark.babygotbackend.repository.GroupRepository;
import com.genspark.babygotbackend.repository.UserRepository;
import com.genspark.babygotbackend.security.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        //check password length
        if (user.getPassword().length() < 10) {
            throw new BadRequestException("Password length must be 10 chars minimum");
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

        //TODO: log security event

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                getRoles(user.getUserGroups())
        );
    }

    @Override
    @Transactional
    public ChangePasswordResponse changePassword(NewPassword newpassword, UserDetailsImpl details) {
        User user = this.userRepository.findUserByEmail(details.getEmail().toLowerCase()).get();

        //check for same password
        if (encoder.matches(newpassword.getNew_password(), user.getPassword())) {
            throw new BadRequestException("The passwords must be different");
        }

        //check password length
        if (newpassword.getNew_password().length() < 10) {
            throw new BadRequestException("Password length must be 10 chars minimum");
        }

        //bcrypt encode password
        user.setPassword(encoder.encode(newpassword.getNew_password()));

        this.userRepository.save(user);

        //TODO: log security event

        return new ChangePasswordResponse(user.getEmail().toLowerCase(),
                "The password has been updated successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserResponse> list = new ArrayList<>();
        List<User> users = this.userRepository.findAll();

        //return empty list if no entries in db
        if (users.size() == 0) {
            return list;
        }

        for (User u : users) {
            UserResponse ur = new UserResponse();
            BeanUtils.copyProperties(u, ur);
            ur.setRoles(getRoles(u.getUserGroups()));
            list.add(ur);
        }

        //sort by id ascending
        list.sort(Comparator.comparing(UserResponse::getId));

        return list;
    }

    @Override
    @Transactional
    public DeleteUserResponse deleteUserByEmail(String email, UserDetailsImpl details) {

        //check for ADMINISTRATOR removing self
        if (email.equalsIgnoreCase(details.getEmail())) {
            throw new BadRequestException("cannot remove ADMINISTRATOR account");
        }

        Optional<User> o = userRepository.findUserByEmail(email.toLowerCase());

        //check if user exists
        if (o.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        this.userRepository.deleteUserByEmail(email.toLowerCase());

        //TODO: log security event

        return new DeleteUserResponse(email, "Deleted successfully!");
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
