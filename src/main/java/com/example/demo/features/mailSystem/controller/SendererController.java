package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.request.CreateSendererRequest;
import com.example.demo.features.mailSystem.service.SendererService;
import com.example.demo.features.mailSystem.dto.SendererDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SendererController {
    private static final Logger log = LoggerFactory.getLogger(SendererController.class);
    private final SendererService sendererService;

    public SendererController(SendererService sendererService) {
        this.sendererService = sendererService;
    }

    @GetMapping("/senderers")
    public ResponseEntity<List<SendererDTO>> findAll(){
        List<SendererDTO> sendererDTOList = sendererService.findAll();
        log.info("Find all senderers");
        return ResponseEntity.ok(sendererDTOList);
    }

    @PostMapping("/senderers")
    public ResponseEntity<Void> createSenderer(@RequestBody CreateSendererRequest request){
        sendererService.save(request);
        log.info("Senderer saved");
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/senderers/{id}")
    public ResponseEntity<Void> deleteSenderer(@PathVariable Long id){
        sendererService.deleteById(id);
        log.info("Senderer deleted");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/senderers/{id}")
    public ResponseEntity<Void> updateSenderer(@RequestBody CreateSendererRequest request, @PathVariable Long id){
        sendererService.updateById(id, request);
        log.info("Senderer updated");
        return ResponseEntity.ok().build();
    }



}
