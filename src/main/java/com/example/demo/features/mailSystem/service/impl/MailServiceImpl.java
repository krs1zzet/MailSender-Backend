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
import com.example.demo.features.mailSystem.service.MailService;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import com.example.demo.features.mailSystem.service.ReceiverService;
import com.example.demo.features.mailSystem.service.SenderService;
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

    public MailServiceImpl(ReceiverService receiverService, SenderService senderService, MailTemplateService mailTemplateService, SenderDtoConverter senderDtoConverter, ReceiverDtoConverter receiverDtoConverter, MailTemplateDtoConverter mailTemplateDtoConverter, JavaMailSender javaMailSender) {
        this.receiverService = receiverService;
        this.senderService = senderService;
        this.mailTemplateService = mailTemplateService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID) {
        SenderDTO senderDTO = senderService.findById(senderID);
        Sender sender = new Sender(senderDTO.getId(), senderDTO.getFname(), senderDTO.getLname(), senderDTO.getEmail());

        List<ReceiverDTO> receiverDTOS = receiverService.findAll();
        List<Receiver> receiverList = receiverDTOS.stream().map(receiverDTO -> new Receiver(receiverDTO.getId(), receiverDTO.getFname(), receiverDTO.getLname(), receiverDTO.getEmail(), receiverDTO.getGroupName())).toList();

        MailTemplateDTO mailTemplateDTO = mailTemplateService.findByID(mailTemplateID);
        MailTemplate mailTemplate = new MailTemplate(mailTemplateDTO.getId(), mailTemplateDTO.getHeader(), mailTemplateDTO.getBody());

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
