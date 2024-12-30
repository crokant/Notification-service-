package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_Success() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(id);
        assertFalse(result.isPresent());
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);
        assertNotNull(result);
        assertEquals("testUser", result.getName());
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(user);
    }

    @Test
    void testRegisterUserWithDetails() {
        String username = "testUser";
        String email = "test@example.com";
        String password = "plainPassword";

        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword("encodedPassword");

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.registerUser(username, email, password);

        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testFindByName_Success() {
        String name = "testUser";
        User user = new User();
        user.setName(name);

        when(userRepository.findByName(name)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByName(name);
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }

    @Test
    void testFindByName_NotFound() {
        String name = "nonExistentUser";

        when(userRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<User> result = userService.findByName(name);
        assertFalse(result.isPresent());
    }

    @Test
    void testValidatePassword() {
        String rawPassword = "plainPassword";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        boolean isValid = userService.validatePassword(rawPassword, encodedPassword);
        assertTrue(isValid);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }
}
