package com.example.demo.service;

import com.example.demo.Entity.Mail;
import com.example.demo.dto.MailDTO;
import com.example.demo.dto.converter.MailDtoConverter;
import com.example.demo.dto.request.CreateMailRequest;
import com.example.demo.repo.MailRepository;
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
    }
    @Transactional
    @Override
    public void deleteByID(Long theID) {
        Optional<Mail> mail = mailRepository.findById(theID);
        Mail theMail = mail.orElseThrow(() -> new RuntimeException("did not find the mail - " + theID));
        deleteByID(theID);
    }

//    @Override
//    public void assignSendererByMail(Long mailID, String sendererMail) {
//
//    }
}
