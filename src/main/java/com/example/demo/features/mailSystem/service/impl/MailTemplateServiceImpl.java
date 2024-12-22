package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.converter.MailTemplateDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateMailTemplateRequest;
import com.example.demo.features.mailSystem.repository.MailTemplateRepository;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {
    private final MailTemplateRepository mailTemplateRepository;
    private final MailTemplateDtoConverter mailTemplateDtoConverter;


    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, MailTemplateDtoConverter mailTemplateDtoConverter) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailTemplateDtoConverter = mailTemplateDtoConverter;
    }

    @Override
    public List<MailTemplateDTO> findAll() {
        return mailTemplateDtoConverter.convert(mailTemplateRepository.findAll());
    }

    @Override
    public MailTemplateDTO findByID(Long theID) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(theID);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new RuntimeException("Did not find mail id - " + theID));
        return mailTemplateDtoConverter.convert(theMailTemplate);
    }

    @Transactional
    @Override
    public void save(CreateMailTemplateRequest request) {
        MailTemplate theMailTemplate = new MailTemplate();
        theMailTemplate.setBody(request.getBody());
        theMailTemplate.setHeader(request.getHeader());
        mailTemplateRepository.save(theMailTemplate);
    }

    @Transactional
    @Override
    public void deleteByID(Long theID) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(theID);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new RuntimeException("did not find the mail - " + theID));
        mailTemplateRepository.deleteById(theMailTemplate.getId());
    }

    @Transactional
    @Override
    public void updateByID(Long theID, CreateMailTemplateRequest request) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(theID);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new RuntimeException("did not find the mail - " + theID));
        theMailTemplate.setHeader(request.getHeader());
        theMailTemplate.setBody(request.getBody());
        mailTemplateRepository.save(theMailTemplate);
    }



}
