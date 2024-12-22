package com.icispp.notificationservice.controllers;


import com.icispp.notificationservice.dto.UserInfoResponse;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.UserService;
import com.icispp.notificationservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class PersonalOfficeController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public PersonalOfficeController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);

            Optional<User> userOptional = userService.findByName(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.ok(new UserInfoResponse(user.getName(), user.getEmail(), "user"));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный токен или пользователь не найден.");
    }
}
