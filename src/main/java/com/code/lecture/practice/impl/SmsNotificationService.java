package com.code.lecture.practice.impl;

import com.code.lecture.practice.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        message = "SMS sent....."+ message;
        System.out.println(message);
    }
}
