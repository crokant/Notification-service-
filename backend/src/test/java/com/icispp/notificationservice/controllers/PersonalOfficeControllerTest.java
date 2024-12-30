package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.dto.UserInfoResponse;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.UserService;
import com.icispp.notificationservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonalOfficeControllerTest {

    @InjectMocks
    private PersonalOfficeController personalOfficeController;

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserInfo_Success() {
        String token = "valid.token";
        String username = "testUser";
        User user = new User();
        user.setName(username);
        user.setEmail("test@example.com");

        when(jwtUtil.resolveToken(request)).thenReturn(token);
        when(jwtUtil.validateToken(token)).thenReturn(true);
        when(jwtUtil.getUsernameFromToken(token)).thenReturn(username);
        when(userService.findByName(username)).thenReturn(Optional.of(user));

        ResponseEntity<?> response = personalOfficeController.getUserInfo(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserInfoResponse);
        UserInfoResponse userInfoResponse = (UserInfoResponse) response.getBody();
        assertEquals("testUser", userInfoResponse.getName());
        assertEquals("test@example.com", userInfoResponse.getEmail());
    }

    @Test
    void testGetUserInfo_InvalidToken() {
        String token = "invalid.token";

        when(jwtUtil.resolveToken(request)).thenReturn(token);
        when(jwtUtil.validateToken(token)).thenReturn(false);

        ResponseEntity<?> response = personalOfficeController.getUserInfo(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Неверный токен или пользователь не найден.", response.getBody());
    }

    @Test
    void testGetUserInfo_UserNotFound() {
        String token = "valid.token";
        String username = "testUser";

        when(jwtUtil.resolveToken(request)).thenReturn(token);
        when(jwtUtil.validateToken(token)).thenReturn(true);
        when(jwtUtil.getUsernameFromToken(token)).thenReturn(username);
        when(userService.findByName(username)).thenReturn(Optional.empty());

        ResponseEntity<?> response = personalOfficeController.getUserInfo(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Неверный токен или пользователь не найден.", response.getBody());
    }
}
