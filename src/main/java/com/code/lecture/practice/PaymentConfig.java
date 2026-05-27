package com.code.lecture.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService(){ //this is a factory method --> it returns object of PaymentService
        return  new PaymentService();
    }

}


