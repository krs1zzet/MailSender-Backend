package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.ParticipationDTO;
import com.example.demo.features.mailSystem.dto.request.BaseParticipationRequest;
import com.example.demo.features.mailSystem.dto.request.CreateParticipationRequest;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.ParticipationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ParticipationController {
    private final ParticipationService participationService;
    private final EventService eventService;

    public ParticipationController(ParticipationService participationService, EventService eventService) {
        this.participationService = participationService;
        this.eventService = eventService;
    }

    @GetMapping("/participations")
    public ResponseEntity<List<ParticipationDTO>> findAllParticipations() {
        List<ParticipationDTO> participationDTOList = participationService.findAll();
        log.info("Participations found");
        return ResponseEntity.ok(participationDTOList);
    }

    @GetMapping("/participations/{userId}")
    public ResponseEntity<List<EventDTO>> findParticipationsByUserId(@PathVariable Long userId) {
        List<EventDTO> eventDTOS = eventService.findByUserIdReturnEventDTO(userId);
        log.info("Participations found by user id");
        return ResponseEntity.ok(eventDTOS);
    }

//    @PostMapping("/participations")
//    public ResponseEntity<Void> createParticipation(@RequestBody CreateParticipationRequest request) {
//        participationService.fin(request.getUserId(),request.getEventId());
//        log.info("Participation saved");
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/participations/{id}")
    public ResponseEntity<Void> deleteParticipationById(@PathVariable Long id) {
        participationService.deleteByID(id);
        log.info("Participation deleted");
        return ResponseEntity.ok().build();
    }
}
