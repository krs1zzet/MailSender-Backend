package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.dto.SendererDTO;
import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import com.example.demo.features.mailSystem.service.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")

public class ReceiverController {
    private static final Logger log = LoggerFactory.getLogger(ReceiverController.class);
    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @GetMapping("/receivers")
    public ResponseEntity<List<ReceiverDTO>> findAll(){
        List<ReceiverDTO> receiverDTOList = receiverService.findAll();
        log.info("Finding all Receivers");
        return ResponseEntity.ok(receiverDTOList);
    }

    @PostMapping("/receivers")
    public ResponseEntity<Void> createReceiver(@RequestBody CreateReceiverRequest request){
        receiverService.save(request);
        log.info("Receiver saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/receivers/{receiverId}")
    public ResponseEntity<Void> deleteReceiverById(@PathVariable Long receiverId){
        receiverService.deleteByID(receiverId);
        log.info("Receiver deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/receivers/{receiverId}")
    public ResponseEntity<Void> updateReceiver(@RequestBody CreateReceiverRequest request, @PathVariable Long receiverId){
        receiverService.updateByID(receiverId, request);
        log.info("Receiver updated");
        return ResponseEntity.ok().build();
    }



}