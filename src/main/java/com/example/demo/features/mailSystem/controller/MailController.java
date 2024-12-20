package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.MailDTO;
import com.example.demo.features.mailSystem.dto.request.CreateMailRequest;
import com.example.demo.features.mailSystem.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MailController {
    private static final Logger log = LoggerFactory.getLogger(MailController.class);
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mails")
    public ResponseEntity<List<MailDTO>> findAll(){
        List<MailDTO> mailDTOList = mailService.findAll();
        log.info("Mails found");
        return ResponseEntity.ok(mailDTOList);
    }

    @PostMapping("/mails")
    public ResponseEntity<Void> addMail(@RequestBody CreateMailRequest request){
        mailService.save(request);
        log.info("Mail saved");
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/mails/{mailId}")
    public ResponseEntity<Void> deleteMailById(@PathVariable Long mailId){
        mailService.deleteByID(mailId);
        log.info("Mail deleted");
        return ResponseEntity.ok().build();
    }

}
