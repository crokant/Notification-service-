package com.icispp.notificationservice.controllers;

import com.icispp.notificationservice.models.Manager;
import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public Subscription createSubscription(@RequestParam String name, @RequestParam Manager manager) {
        return subscriptionService.createSubscription(name, manager);
    }

    @PostMapping("/addUser")
    public Subscription addUserToSubscription(@RequestParam User user, @RequestParam Subscription subscription) {
        return subscriptionService.addUserToSubscription(user, subscription);
    }
}
