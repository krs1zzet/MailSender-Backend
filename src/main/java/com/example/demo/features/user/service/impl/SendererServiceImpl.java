package com.example.demo.features.user.service.impl;

import com.example.demo.features.user.entity.Senderer;
import com.example.demo.features.user.dto.SendererDTO;
import com.example.demo.features.user.dto.converter.SendererDtoConverter;
import com.example.demo.features.user.dto.request.CreateSendererRequest;
import com.example.demo.features.user.repository.SendererRepository;
import com.example.demo.features.user.service.SendererService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SendererServiceImpl implements SendererService {

    private final SendererRepository sendererRepository;
    private final SendererDtoConverter sendererDtoConverter;

    public SendererServiceImpl(SendererRepository sendererRepository, SendererDtoConverter sendererDtoConverter) {
        this.sendererRepository = sendererRepository;
        this.sendererDtoConverter = sendererDtoConverter;
    }


    public SendererDTO findById(Long theId){
        Optional<Senderer> senderer = sendererRepository.findById(theId);
        Senderer theSenderer = senderer.orElseThrow(() -> new RuntimeException("Did not find mail id - " + theId));
        return sendererDtoConverter.convert(theSenderer);
    }

    @Override
    public List<SendererDTO> findAll() {
        return sendererDtoConverter.convert(sendererRepository.findAll());
    }

    @Override
    public void save(CreateSendererRequest request) {
        Senderer senderer = new Senderer();
        senderer.setEmail(request.getEmail());
        senderer.setFname(request.getFname());
        senderer.setLname(request.getLname());
        sendererRepository.save(senderer);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Senderer> senderer = sendererRepository.findById(id);
        Senderer theSenderer = senderer.orElseThrow(()-> new RuntimeException("did not find the id - "+ id));
        sendererRepository.deleteById(theSenderer.getId());
    }
}
