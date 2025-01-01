package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.request.CreateEventRequest;

import java.util.List;

public interface EventService {
    EventDTO findById(Long id);
    List<EventDTO> findAll();
    void save(CreateEventRequest request);
    void deleteById(Long id);
    void updateById(Long id, CreateEventRequest request);
}
