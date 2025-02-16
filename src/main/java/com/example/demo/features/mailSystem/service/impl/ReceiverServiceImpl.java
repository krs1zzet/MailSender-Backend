package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.converter.ReceiverDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.repository.ReceiverRepository;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.ReceiverService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    private final ReceiverRepository receiverRepository;
    private final ReceiverDtoConverter receiverDtoConverter;
    private final EventService eventService;

    public ReceiverServiceImpl(ReceiverRepository receiverRepository, ReceiverDtoConverter receiverDtoConverter, EventService eventService) {
        this.receiverRepository = receiverRepository;
        this.receiverDtoConverter = receiverDtoConverter;
        this.eventService = eventService;
    }

    @Transactional
    @Override
    public void save(CreateReceiverRequest request) {
        Receiver receiver = new Receiver();
        receiver.setEmail(request.getEmail());
        receiver.setLname(request.getLname());
        receiver.setFname(request.getFname());
        receiver.setGroupName(request.getGroupName());
        receiver.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
        receiverRepository.save(receiver);
    }
    @Transactional
    @Override
    public void deleteByID(Long id) {
        Optional<Receiver> receiver = receiverRepository.findById(id);
        Receiver theReceiver = receiver.orElseThrow(()-> new RuntimeException("did not find the id - "+ id));
        receiverRepository.deleteById(theReceiver.getId());
    }

    @Override
    public ReceiverDTO findById(Long id) {
        Optional<Receiver> receiver = receiverRepository.findById(id);
        Receiver theReceiver = receiver.orElseThrow(()-> new RuntimeException("did not find the id - "+ id));
        return receiverDtoConverter.convert(theReceiver);
    }

    @Override
    public List<ReceiverDTO> findAll() {
        return receiverDtoConverter.convert(receiverRepository.findAll());
    }

    @Transactional
    @Override
    public void updateByID(Long theID, CreateReceiverRequest request) {
        Optional<Receiver> receiver = receiverRepository.findById(theID);
        Receiver theReceiver = receiver.orElseThrow(() -> new RuntimeException("did not find the mail - " + theID));
        theReceiver.setFname(request.getFname());
        theReceiver.setLname(request.getLname());
        theReceiver.setEmail(request.getEmail());
        theReceiver.setGroupName(request.getGroupName());
        theReceiver.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
    }

    @Override
    public List<ReceiverDTO> findReceiversByEventId(Long theID) {
        List<Receiver> receivers = receiverRepository.findByEventId(theID);
        if (receivers.isEmpty()) {
            throw new RuntimeException("Did not find any receivers for event id - " + theID);
        }
        return receiverDtoConverter.convert(receivers);
    }

}
