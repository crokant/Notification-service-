package com.icispp.notificationservice.services;
// Importing required classes
import com.icispp.notificationservice.Entity.EmailDetails;

// Interface
public interface EmailService {

    String sendSimpleMail(EmailDetails details) throws Exception;
    String sendMailWithAttachment(EmailDetails details);
}