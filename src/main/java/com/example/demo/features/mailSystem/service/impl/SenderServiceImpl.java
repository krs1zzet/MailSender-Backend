package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.entity.Sender;
import com.example.demo.features.mailSystem.dto.SenderDTO;
import com.example.demo.features.mailSystem.dto.converter.SenderDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateSenderRequest;
import com.example.demo.features.mailSystem.repository.SenderRepository;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.SenderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class SenderServiceImpl implements SenderService {

    private final SenderRepository senderRepository;
    private final SenderDtoConverter senderDtoConverter;
    private final EventService eventService;

    public SenderServiceImpl(SenderRepository senderRepository, SenderDtoConverter senderDtoConverter, EventService eventService) {
        this.senderRepository = senderRepository;
        this.senderDtoConverter = senderDtoConverter;
        this.eventService = eventService;
    }


    public SenderDTO findById(Long theId){
        Optional<Sender> senderer = senderRepository.findById(theId);
        Sender theSender = senderer.orElseThrow(() -> new RuntimeException("Did not find mail id - " + theId));
        return senderDtoConverter.convert(theSender);
    }

    @Override
    public List<SenderDTO> findAll() {
        return senderDtoConverter.convert(senderRepository.findAll());
    }

    @Override
    public void save(CreateSenderRequest request) {
        Sender sender = new Sender();
        sender.setEmail(request.getEmail());
        sender.setCreatedAt(LocalDateTime.now());
        sender.setEncryptedPassword(request.getPassword());
        sender.setLastUsedAt(LocalDateTime.now());
        sender.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
        senderRepository.save(sender);
    }

    @Override
    public void updateLastUsedAt(Long senderId) {
        Sender sender = senderRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        sender.setLastUsedAt(LocalDateTime.now());
        senderRepository.save(sender);
    }

    @Override
    public String findPasswordBySenderId(Long senderId) {
        Sender sender = senderRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        return sender.getEncryptedPassword();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Sender> senderer = senderRepository.findById(id);
        Sender theSender = senderer.orElseThrow(()-> new RuntimeException("did not find the id - "+ id));
        senderRepository.deleteById(theSender.getId());
    }
    @Override
    @Transactional
    public void updateById(Long id, CreateSenderRequest request) {
        Optional<Sender> senderer = senderRepository.findById(id);
        Sender theSender = senderer.orElseThrow(()-> new RuntimeException("did not find the id - "+ id));
        theSender.setEmail(request.getEmail());
        theSender.setCreatedAt(LocalDateTime.now());
        theSender.setEncryptedPassword(request.getPassword());
        theSender.setLastUsedAt(LocalDateTime.now());
        theSender.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
        senderRepository.save(theSender);
    }

    @Override
    public List<SenderDTO> findSendersByEventId(Long theID) {
        List<Sender> senders = senderRepository.findByEventId(theID);
        if (senders.isEmpty()) {
            throw new RuntimeException("Did not find any senders for event id - " + theID);
        }
        return senderDtoConverter.convert(senders);
    }
}
