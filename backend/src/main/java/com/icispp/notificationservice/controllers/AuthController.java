package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.dto.AuthRequest;
import com.icispp.notificationservice.dto.AuthResponse;
import com.icispp.notificationservice.dto.RegisterRequest;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.UserService;
import com.icispp.notificationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
            System.out.println("Пользователь успешно зарегистрирован" + request.getUsername() + " " + request.getEmail() + " " + request.getPassword());    

            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка регистрации: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println(authRequest.getUsername() + " " + authRequest.getPassword());  
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage() + e.sta);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные учетные данные");
        }
    }

}



