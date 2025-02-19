package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.converter.EventDtoConverter;
import com.example.demo.features.mailSystem.dto.converter.MailTemplateDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateEventRequest;
import com.example.demo.features.mailSystem.entity.Event;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.entity.Sender;
import com.example.demo.features.mailSystem.repository.EventRepository;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import com.example.demo.product.exceptions.generic.eventExceptions.NotFoundExceptions.EventIdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventDtoConverter eventDtoConverter;


    public EventServiceImpl(EventRepository eventRepository, EventDtoConverter eventDtoConverter) {
        this.eventRepository = eventRepository;
        this.eventDtoConverter = eventDtoConverter;
    }

    @Override
    public EventDTO findById(Long id) {
        Optional<Event> eventDTO = eventRepository.findById(id);
        Event event = eventDTO.orElseThrow(() -> new EventIdNotFoundException(id));
        return eventDtoConverter.convert(event);
    }

    @Override
    public Event findById_ReturnEvent(Long id) {
        Optional<Event> eventDTO = eventRepository.findById(id);
        return eventDTO.orElseThrow(() -> new EventIdNotFoundException(id));
    }


    @Override
    public List<EventDTO> findAll() {
        return eventDtoConverter.convert(eventRepository.findAll());
    }

    @Override
    public void save(CreateEventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        Event theEvent = event.orElseThrow(() -> new EventIdNotFoundException(id));
        eventRepository.deleteById(theEvent.getId());

    }

    @Override
    public void updateById(Long id, CreateEventRequest request) {
        Optional<Event> event = eventRepository.findById(id);
        Event theEvent = event.orElseThrow(() -> new EventIdNotFoundException(id));
        theEvent.setName(request.getName());
        theEvent.setDescription(request.getDescription());
        theEvent.setDate(request.getDate());
        eventRepository.save(theEvent);

    }


}
