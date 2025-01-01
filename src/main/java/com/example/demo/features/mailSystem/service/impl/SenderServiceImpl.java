package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.entity.Sender;
import com.example.demo.features.mailSystem.dto.SenderDTO;
import com.example.demo.features.mailSystem.dto.converter.SenderDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateSenderRequest;
import com.example.demo.features.mailSystem.repository.SenderRepository;
import com.example.demo.features.mailSystem.service.SenderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SenderServiceImpl implements SenderService {

    private final SenderRepository senderRepository;
    private final SenderDtoConverter senderDtoConverter;

    public SenderServiceImpl(SenderRepository senderRepository, SenderDtoConverter senderDtoConverter) {
        this.senderRepository = senderRepository;
        this.senderDtoConverter = senderDtoConverter;
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
        sender.setFname(request.getFname());
        sender.setLname(request.getLname());
        senderRepository.save(sender);
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
        theSender.setFname(request.getFname());
        theSender.setLname(request.getLname());
        senderRepository.save(theSender);
    }
}
