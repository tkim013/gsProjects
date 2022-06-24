package com.genspark.backend;


import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.EmailServiceImpl;

public class EmailTest {
    public static void main(String[] args) {


        EmailService emailService = new EmailServiceImpl();
        String receiver="catdogramb@gmail.com";//write here receiver id
        emailService.sendEmail(receiver,"hi","welcome", true);

        System.out.println("success");
    }
}
