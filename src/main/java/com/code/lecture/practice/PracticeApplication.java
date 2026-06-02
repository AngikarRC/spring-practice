package com.code.lecture.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication implements CommandLineRunner {
    @Autowired
    PaymentService paymentService;  //autowired - field injection - not preferred


    private final NotificationService notificationService; //make it final --> immutable

    /**
     *
     * @param notificationService
     * constructor dependency injection - most preferred in prod
     * no @Autowired need
     */
    public PracticeApplication(NotificationService notificationService){
        this.notificationService = notificationService;
    }

	public static void main(String[] args) {

        SpringApplication.run(PracticeApplication.class, args);

	}

    /**
     * to get all instances of the bean created
     * @param args
     * @throws Exception
     *
    @Autowired
    Map<String,NotificationService> notificationServiceMapObj = new HashMap<>();
     */

    @Override
    public void run(String... args) throws Exception {
        paymentService.pay();
        String msg = paymentService.greet("Angikar");
        System.out.println(msg);

        notificationService.send("Verified");
        /*
        for(var notificationService : notificationServiceMapObj.entrySet()){
            System.out.println(notificationService.getKey());
            notificationService.getValue().send("CODE");
        } */
    }
}
