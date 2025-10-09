package com.example.demo.features.mailSystem.controller;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.request.CreateEventRequest;
import com.example.demo.features.mailSystem.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
            @GetMapping("/events")
            public ResponseEntity<List<EventDTO>> findAll(){
                List<EventDTO> eventDTOList = eventService.findEventsBySignedUser();
                log.info("Find all events");
                return ResponseEntity.ok(eventDTOList);
            }

    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(@RequestBody CreateEventRequest request){
        eventService.save(request);
        log.info("Event saved");
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteById(id);
        log.info("Event deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Void> updateEvent(@RequestBody CreateEventRequest request, @PathVariable Long id) {
        eventService.updateById(id, request);
        log.info("Event updated");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id){
        EventDTO eventDTO = eventService.findById(id);
        log.info("Find event by id");
        return ResponseEntity.ok(eventDTO);
    }
}
