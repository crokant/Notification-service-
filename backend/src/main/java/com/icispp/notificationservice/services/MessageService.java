package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Message;
import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.MessageRepository;
import com.icispp.notificationservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository, SubscriptionRepository subscriptionRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessageToUser(String subject, String content, User user, Subscription subscription) {
        Message message = Message.builder()
                .subject(subject)
                .content(content)
                .user(user)
                .subscription(subscription)
                .sentAt(LocalDateTime.now())
                .delivered(false)
                .build();
        return messageRepository.save(message);
    }

    public void sendMessageToSubscribers(String subject, String content, Subscription subscription) {
        subscription.getSubscribers().forEach(user -> sendMessageToUser(subject, content, user, subscription));
    }
}
