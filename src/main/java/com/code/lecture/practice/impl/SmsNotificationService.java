package com.code.lecture.practice.impl;

import com.code.lecture.practice.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sms")
@ConditionalOnProperty(name = "notification.type" , havingValue = "sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        message = "SMS sent....."+ message;
        System.out.println(message);
    }
}
