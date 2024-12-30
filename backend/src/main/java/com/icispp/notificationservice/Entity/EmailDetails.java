package com.icispp.notificationservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.icispp.notificationservice.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    public EmailDetails(Message message)
    {
        subject = message.getSubject();
        msgBody = message.getContent();
    }
}