package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send-mail")
    public ResponseEntity<Void> sendMail(@RequestParam Long senderID,
                                         @RequestParam List<Long> receiverIDs,
                                         @RequestParam Long mailTemplateID) {
        if (receiverIDs == null || receiverIDs.isEmpty()) {
            throw new IllegalArgumentException("receiverIDs parameter is required and cannot be empty");
        }
        mailService.sendMail(senderID, receiverIDs, mailTemplateID);
        log.info("Mail sent");
        return ResponseEntity.ok().build();
    }

}
