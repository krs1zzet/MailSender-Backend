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
import com.example.demo.features.mailSystem.service.ParticipationService;
import com.example.demo.features.user.entity.UserEntity;
import com.example.demo.features.user.repo.UserRepository;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.exceptions.generic.eventExceptions.NotFoundExceptions.EventIdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventDtoConverter eventDtoConverter;
    private final UserService userService;
    private final ParticipationService participationService;



    public EventServiceImpl(EventRepository eventRepository, EventDtoConverter eventDtoConverter, UserService userService, ParticipationService participationService) {
        this.eventRepository = eventRepository;
        this.eventDtoConverter = eventDtoConverter;
        this.userService = userService;
        this.participationService = participationService;
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
    public List<EventDTO> findByUserIdReturnEventDTO(long userId) {
        List<Long> eventIds= participationService.findEventIdsByUserId(userId);
        return eventIds.stream().map(this::findById).toList();
    }

    @Override
    public List<EventDTO> findEventsBySignedUser() {
        UserEntity user = userService.findAuthenticatedUser();
        log.info("User found: {}", user.getUsername());
        List<Long> eventIds = participationService.findEventIdsByUserId(user.getId());
        return eventIds.stream().map(this::findById).toList();
    }


    @Override
    public List<EventDTO> findAll() {
        return eventDtoConverter.convert(eventRepository.findAll());
    }

    @Transactional
    @Override
    public void save(CreateEventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        UserEntity user = userService.findAuthenticatedUser();
        eventRepository.save(event);

        participationService.save(user,event);
    }

    @Transactional
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
