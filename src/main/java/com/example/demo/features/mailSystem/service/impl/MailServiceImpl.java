package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.dto.SenderDTO;
import com.example.demo.features.mailSystem.dto.converter.MailTemplateDtoConverter;
import com.example.demo.features.mailSystem.dto.converter.ReceiverDtoConverter;
import com.example.demo.features.mailSystem.dto.converter.SenderDtoConverter;
import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.entity.Sender;
import com.example.demo.features.mailSystem.service.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final ReceiverService receiverService;
    private final SenderService senderService;
    private final MailTemplateService mailTemplateService;
    private final EventService eventService;

    public MailServiceImpl(ReceiverService receiverService, SenderService senderService, MailTemplateService mailTemplateService, SenderDtoConverter senderDtoConverter, ReceiverDtoConverter receiverDtoConverter, MailTemplateDtoConverter mailTemplateDtoConverter, JavaMailSender javaMailSender, EventService eventService) {
        this.receiverService = receiverService;
        this.senderService = senderService;
        this.mailTemplateService = mailTemplateService;
        this.javaMailSender = javaMailSender;
        this.eventService = eventService;
    }

    @Override
    public void sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID) {
        SenderDTO senderDTO = senderService.findById(senderID);
        Sender sender = new Sender(senderDTO.getId(), senderDTO.getFname(), senderDTO.getLname(), senderDTO.getEmail(),eventService.findById_ReturnEvent(senderDTO.getEventId()));

        List<ReceiverDTO> receiverDTOS = receiverService.findAll();
        List<Receiver> receiverList = receiverDTOS.stream().map(receiverDTO -> new Receiver(receiverDTO.getId(), receiverDTO.getFname(), receiverDTO.getLname(), receiverDTO.getEmail(), receiverDTO.getGroupName(),eventService.findById_ReturnEvent(receiverDTO.getEventId()))).toList();

        MailTemplateDTO mailTemplateDTO = mailTemplateService.findByID(mailTemplateID);
        MailTemplate mailTemplate = new MailTemplate(mailTemplateDTO.getId(), mailTemplateDTO.getHeader(), mailTemplateDTO.getBody(),eventService.findById_ReturnEvent(mailTemplateDTO.getEventId()));

        for (Receiver receiver : receiverList) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender.getEmail());
            mailMessage.setTo(receiver.getEmail());
            mailMessage.setSubject(mailTemplate.getHeader());
            mailMessage.setText(mailTemplate.getBody());
            javaMailSender.send(mailMessage);
        }
    }
}
