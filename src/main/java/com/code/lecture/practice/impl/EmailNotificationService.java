package com.code.lecture.practice.impl;

import com.code.lecture.practice.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        message = "Email sent....." + message;
        System.out.println(message);
    }
}
