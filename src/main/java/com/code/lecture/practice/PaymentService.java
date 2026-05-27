package com.code.lecture.practice;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public void pay(){
        System.out.println("paying..");
    }

    String greet(String name){
        return "Hello " + name;
    }
}
