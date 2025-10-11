package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.request.BaseSenderRequest;
import com.example.demo.features.mailSystem.dto.request.CreateSenderRequest;
import com.example.demo.features.mailSystem.service.SenderService;
import com.example.demo.features.mailSystem.dto.SenderDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SenderController {
    private static final Logger log = LoggerFactory.getLogger(SenderController.class);
    private final SenderService senderService;

    public SenderController(SenderService senderService) {
        this.senderService = senderService;
    }

    @GetMapping("/senders")
    public ResponseEntity<List<SenderDTO>> findAll(){
        List<SenderDTO> senderDTOList = senderService.findAll();
        log.info("Find all senders");
        return ResponseEntity.ok(senderDTOList);
    }
    @GetMapping("/senders/{eventId}")
    public ResponseEntity<List<SenderDTO>> findSendersByEventId(@PathVariable Long eventId){
        List<SenderDTO> senderDTOList = senderService.findSendersByEventId(eventId);
        log.info("Find all senders by event id");
        return ResponseEntity.ok(senderDTOList);
    }

    @PostMapping("/senders")
    public ResponseEntity<Void> createSenderer(@Valid @RequestBody CreateSenderRequest request){
        senderService.save(request);
        log.info("Sender saved");
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/senders/{id}")
    public ResponseEntity<Void> deleteSenderer(@PathVariable Long id){
        senderService.deleteById(id);
        log.info("Sender deleted");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/senders/{id}")
    public ResponseEntity<Void> updateSenderer(@RequestBody BaseSenderRequest request, @PathVariable Long id){
        senderService.updateById(id, request);
        log.info("Senderer updated");
        return ResponseEntity.ok().build();
    }



}
