package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.request.CreateMailTemplateRequest;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api")

public class MailTemplateController {
    private static final Logger log = LoggerFactory.getLogger(MailTemplateController.class);
    private final MailTemplateService mailTemplateService;

    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @GetMapping("/mails")
    public ResponseEntity<List<MailTemplateDTO>> findAllMailTemplates(){
        List<MailTemplateDTO> mailTemplateDTOList = mailTemplateService.findAll();
        log.info("Mails found");
        return ResponseEntity.ok(mailTemplateDTOList);
    }

    @PostMapping("/mails")
    public ResponseEntity<Void> createMailTemplate(@RequestBody CreateMailTemplateRequest request){
        mailTemplateService.save(request);
        log.info("Mail saved");
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/mails/{mailId}")
    public ResponseEntity<Void> deleteMailTemplateById(@PathVariable Long mailId){
        mailTemplateService.deleteByID(mailId);
        log.info("Mail deleted");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/mails/{mailId}")
    public ResponseEntity<Void> updateMailTemplate(@RequestBody CreateMailTemplateRequest request, @PathVariable Long mailId){
        mailTemplateService.updateByID(mailId, request);
        log.info("Mail updated");
        return ResponseEntity.ok().build();
    }


}
