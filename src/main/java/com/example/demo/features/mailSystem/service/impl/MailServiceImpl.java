package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.entity.Mail;
import com.example.demo.features.mailSystem.dto.MailDTO;
import com.example.demo.features.mailSystem.dto.converter.MailDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateMailRequest;
import com.example.demo.features.mailSystem.repository.MailRepository;
import com.example.demo.features.mailSystem.service.MailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailServiceImpl implements MailService {
    private final MailRepository mailRepository;
    private final MailDtoConverter mailDtoConverter;


    public MailServiceImpl(MailRepository mailRepository, MailDtoConverter mailDtoConverter) {
        this.mailRepository = mailRepository;
        this.mailDtoConverter = mailDtoConverter;
    }

    @Override
    public List<MailDTO> findAll() {
        return mailDtoConverter.convert(mailRepository.findAll());
    }

    @Override
    public MailDTO findByID(Long theID) {
        Optional<Mail> mail = mailRepository.findById(theID);
        Mail theMail = mail.orElseThrow(() -> new RuntimeException("Did not find mail id - " + theID));
        return mailDtoConverter.convert(theMail);
    }

    @Transactional
    @Override
    public void save(CreateMailRequest request) {
        Mail theMail = new Mail();
        theMail.setBody(request.getBody());
        theMail.setHeader(request.getHeader());
        mailRepository.save(theMail);
    }

    @Transactional
    @Override
    public void deleteByID(Long theID) {
        Optional<Mail> mail = mailRepository.findById(theID);
        Mail theMail = mail.orElseThrow(() -> new RuntimeException("did not find the mail - " + theID));
        deleteByID(theMail.getId());
    }

//    @Override
//    public void assignSendererByMail(Long mailID, String sendererMail) {
//
//    }
}
