package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription addUserToSubscription(User user, Subscription subscription) {
        subscription.getSubscribers().add(user);
        return subscriptionRepository.save(subscription);
    }

    public Subscription createSubscription(String name, User creator) {
        Subscription subscription = Subscription.builder()
                .name(name)
                .creator(creator)
                .build();
        return subscriptionRepository.save(subscription);
    }
}
