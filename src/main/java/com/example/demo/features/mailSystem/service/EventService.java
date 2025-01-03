package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.request.CreateEventRequest;
import com.example.demo.features.mailSystem.entity.Event;
import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.entity.Sender;

import java.util.List;

public interface EventService {
    EventDTO findById(Long id);
    List<EventDTO> findAll();
    void save(CreateEventRequest request);
    void deleteById(Long id);
    void updateById(Long id, CreateEventRequest request);
    Event findById_ReturnEvent(Long id);
}
