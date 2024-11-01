package com.example.demo.controller;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {

    private final MailSender mailSender;

    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/send-mail")
    public String sendEmail(){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("izzettin.karasayar@gmail.com");
            message.setTo("myelman17@gmail.com");
            message.setSubject("ornek baslik");
            message.setText("ornek mail bodysi");

            mailSender.send(message);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }


    }
}
