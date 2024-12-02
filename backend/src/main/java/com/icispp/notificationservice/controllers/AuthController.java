package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "Пользователь успешно зарегистрирован";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        User user = userService.findByName(username).orElseThrow(() -> new RuntimeException("No such user"));
        if(!userService.validatePassword(password, user.getPassword())){
            return "неправильный пароль";
        }
        return "Login successful";
    }



}



