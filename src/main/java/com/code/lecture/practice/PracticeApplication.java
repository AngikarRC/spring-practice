package com.code.lecture.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication implements CommandLineRunner {
    @Autowired
    PaymentService paymentService;
	public static void main(String[] args) {

        SpringApplication.run(PracticeApplication.class, args);

	}

    @Override
    public void run(String... args) throws Exception {
        paymentService.pay();
        String msg = paymentService.greet("Angikar");
        System.out.println(msg);
    }
}
