package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.MailDTO;
import com.example.demo.features.mailSystem.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> sendMail(@RequestParam Long senderID,
                                      @RequestParam List<Long> receiverIDs,
                                      @RequestParam Long mailTemplateID) {

        MailDTO response = mailService.sendMail(senderID, receiverIDs, mailTemplateID);

        if (!response.isSuccess()) {
            log.error("Mail sending failed for some recipients: {}", response.getFailedEmails());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        log.info("Mail sent successfully");
        return ResponseEntity.ok(response);

    }
}
