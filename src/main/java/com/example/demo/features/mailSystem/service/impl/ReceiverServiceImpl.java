package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.converter.ReceiverDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.entity.Senderer;
import com.example.demo.features.mailSystem.repository.ReceiverRepository;
import com.example.demo.features.mailSystem.service.ReceiverService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    private final ReceiverRepository receiverRepository;
    private final ReceiverDtoConverter receiverDtoConverter;

    public ReceiverServiceImpl(ReceiverRepository receiverRepository, ReceiverDtoConverter receiverDtoConverter) {
        this.receiverRepository = receiverRepository;
        this.receiverDtoConverter = receiverDtoConverter;
    }

    @Transactional
    @Override
    public void save(CreateReceiverRequest request) {
        Receiver receiver = new Receiver();
        receiver.setEmail(request.getEmail());
        receiver.setLname(request.getLname());
        receiver.setFname(request.getFname());
        receiver.setGroupName(request.getGroupName());
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
    public ReceiverDTO findById(CreateReceiverRequest request) {
        Optional<Receiver> receiver = receiverRepository.findById(request.getId());
        Receiver theReceiver = receiver.orElseThrow(()-> new RuntimeException("did not find the id - "+ request.getId()));
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
    }

}
