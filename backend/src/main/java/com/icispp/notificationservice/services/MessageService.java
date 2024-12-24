package com.icispp.notificationservice.services;

import com.icispp.notificationservice.Entity.EmailDetails;
import com.icispp.notificationservice.models.Message;
import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.MessageRepository;
import com.icispp.notificationservice.repositories.SubscriptionRepository;
import com.icispp.notificationservice.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final EmailServiceImpl emailService;


    @Autowired
    public MessageService(MessageRepository messageRepository, SubscriptionRepository subscriptionRepository, EmailServiceImpl emailService) {
        this.messageRepository = messageRepository;
        this.emailService = emailService;
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

        emailService.sendSimpleMailToUser(message, user);

        return messageRepository.save(message);
    }

    public void sendMessageToSubscribers(String subject, String content, Subscription subscription) {
        subscription.getUsers().forEach(user -> sendMessageToUser(subject, content, user, subscription));
    }
}
