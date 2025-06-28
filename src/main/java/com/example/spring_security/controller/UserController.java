package com.example.spring_security.controller;

import com.example.spring_security.entity.User;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) {
        return userService.verify(request);
    }

}
