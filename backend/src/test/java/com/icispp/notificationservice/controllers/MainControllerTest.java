package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.MessageService;
import com.icispp.notificationservice.services.SubscriptionService;
import com.icispp.notificationservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private MessageService messageService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHello() {
        Map<String, String> response = mainController.hello("http://example.com");
        assertEquals("Hello from the server!", response.get("message"));
        assertEquals("http://example.com", response.get("origin"));
    }

    @Test
    void testAddUserToSubscription() {
        Long subscriptionId = 1L;
        Long userId = 2L;
        Subscription subscription = new Subscription();
        User user = new User();

        when(subscriptionService.findById(subscriptionId)).thenReturn(Optional.of(subscription));
        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(subscriptionService.addUserToSubscription(user, subscription)).thenReturn(subscription);

        Subscription result = mainController.addUserToSubscription(subscriptionId, userId);
        assertNotNull(result);
        verify(subscriptionService).addUserToSubscription(user, subscription);
    }

    @Test
    void testAddUserToSubscription_InvalidSubscription() {
        Long subscriptionId = 1L;
        Long userId = 2L;

        when(subscriptionService.findById(subscriptionId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mainController.addUserToSubscription(subscriptionId, userId);
        });

        assertEquals("Invalid subscription ID", exception.getMessage());
    }

    @Test
    void testSendMessageToSubscribers() {
        Long subscriptionId = 1L;
        String subject = "Test Subject";
        String content = "Test Content";
        Subscription subscription = new Subscription();

        when(subscriptionService.findById(subscriptionId)).thenReturn(Optional.of(subscription));

        mainController.sendMessageToSubscribers(subscriptionId, subject, content);
        verify(messageService).sendMessageToSubscribers(subject, content, subscription);
    }

    @Test
    void testSendMessageToSubscribers_InvalidSubscription() {
        Long subscriptionId = 1L;
        String subject = "Test Subject";
        String content = "Test Content";

        when(subscriptionService.findById(subscriptionId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mainController.sendMessageToSubscribers(subscriptionId, subject, content);
        });

        assertEquals("Invalid subscription ID", exception.getMessage());
    }
}
