package com.example.Test_Application.controller;

import com.example.Test_Application.model.User;
import com.example.Test_Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/testdb")
    public String testDatabaseConnection() {
        User user = new User();
        user.setName("Test User");

        userRepository.save(user);

        Optional<User> retrievedUser = userRepository.findById(user.getId());

        return retrievedUser.isPresent() ? "Connection successful! User saved and retrieved: " + retrievedUser.get().getName() : "Connection failed!";
    }
}
