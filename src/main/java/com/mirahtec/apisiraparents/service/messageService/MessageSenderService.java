package com.mirahtec.apisiraparents.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSenderService {
    public void sendEmail(String to, String subject, String text) {
        log.info("Sending email to: {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        log.info("Message sent: {}", message.toString());
        // mailSender.send(message);
    }
    public void sendSMS(String phoneNumber, String text) {
        log.info("Sending SMS to: {}", phoneNumber);
        log.info("Message sent: {}", text);
    }
    public void sendNotification(String to, String text) {
        log.info("Sending notification to: {}", to);
        log.info("Message sent: {}", text);
    }

}