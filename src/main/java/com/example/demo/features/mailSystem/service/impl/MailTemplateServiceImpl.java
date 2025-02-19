package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.converter.MailTemplateDtoConverter;
import com.example.demo.features.mailSystem.dto.request.CreateMailTemplateRequest;
import com.example.demo.features.mailSystem.repository.MailTemplateRepository;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import com.example.demo.product.exceptions.generic.mailTemplateExceptions.NotFoundExceptions.MailTemplateIdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {
    private final MailTemplateRepository mailTemplateRepository;
    private final MailTemplateDtoConverter mailTemplateDtoConverter;
    private final EventService eventService;


    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, MailTemplateDtoConverter mailTemplateDtoConverter, EventService eventService) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailTemplateDtoConverter = mailTemplateDtoConverter;
        this.eventService = eventService;
    }

    @Override
    public List<MailTemplateDTO> findAll() {
        return mailTemplateDtoConverter.convert(mailTemplateRepository.findAll());
    }

    @Override
    public MailTemplateDTO findByID(Long id) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(id);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new MailTemplateIdNotFoundException(id));
        return mailTemplateDtoConverter.convert(theMailTemplate);
    }

    @Transactional
    @Override
    public void save(CreateMailTemplateRequest request) {
        MailTemplate theMailTemplate = new MailTemplate();
        theMailTemplate.setBody(request.getBody());
        theMailTemplate.setHeader(request.getHeader());
        theMailTemplate.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
        mailTemplateRepository.save(theMailTemplate);
    }

    @Transactional
    @Override
    public void deleteByID(Long id) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(id);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new MailTemplateIdNotFoundException(id));
        mailTemplateRepository.deleteById(theMailTemplate.getId());
    }

    @Transactional
    @Override
    public void updateByID(Long id, CreateMailTemplateRequest request) {
        Optional<MailTemplate> mail = mailTemplateRepository.findById(id);
        MailTemplate theMailTemplate = mail.orElseThrow(() -> new MailTemplateIdNotFoundException(id));
        theMailTemplate.setHeader(request.getHeader());
        theMailTemplate.setBody(request.getBody());
        theMailTemplate.setEvent(eventService.findById_ReturnEvent(request.getEventId()));
        mailTemplateRepository.save(theMailTemplate);
    }

    @Override
    public List<MailTemplateDTO> findMailTemplatesByEventId(Long id) {
        List<MailTemplate> mailTemplates = mailTemplateRepository.findByEventId(id);

        return mailTemplateDtoConverter.convert(mailTemplates);
    }



}
