package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.models.Manager;
import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.MessageService;
import com.icispp.notificationservice.services.SubscriptionService;
import com.icispp.notificationservice.services.ManagerService;
import com.icispp.notificationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    private final SubscriptionService subscriptionService;
    private final MessageService messageService;
    private final ManagerService managerService;
    private final UserService userService;

    @Autowired
    public MainController(SubscriptionService subscriptionService, MessageService messageService,
                          ManagerService managerService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.messageService = messageService;
        this.managerService = managerService;
        this.userService = userService;
    }

    @PostMapping("/managers/{managerId}/createSubscription")
    public Subscription createSubscription(@PathVariable Long managerId, @RequestParam String subscriptionName) {
        Manager manager = managerService.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID"));
        return subscriptionService.createSubscription(subscriptionName, manager);
    }

    @PostMapping("/subscriptions/{subscriptionId}/addUser")
    public Subscription addUserToSubscription(@PathVariable Long subscriptionId, @RequestParam Long userId) {
        Subscription subscription = subscriptionService.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subscription ID"));
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        return subscriptionService.addUserToSubscription(user, subscription);
    }

    @PostMapping("/subscriptions/{subscriptionId}/sendMessage")
    public void sendMessageToSubscribers(
            @PathVariable Long subscriptionId,
            @RequestParam String subject,
            @RequestParam String content) {

        Subscription subscription = subscriptionService.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subscription ID"));
        messageService.sendMessageToSubscribers(subject, content, subscription);
    }
}
