package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.dto.SendererDTO;
import com.example.demo.features.mailSystem.dto.converter.MailTemplateDtoConverter;
import com.example.demo.features.mailSystem.dto.converter.ReceiverDtoConverter;
import com.example.demo.features.mailSystem.dto.converter.SendererDtoConverter;
import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.entity.Senderer;
import com.example.demo.features.mailSystem.service.MailService;
import com.example.demo.features.mailSystem.service.MailTemplateService;
import com.example.demo.features.mailSystem.service.ReceiverService;
import com.example.demo.features.mailSystem.service.SendererService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final ReceiverService receiverService;
    private final SendererService sendererService;
    private final MailTemplateService mailTemplateService;

    public MailServiceImpl(ReceiverService receiverService, SendererService sendererService, MailTemplateService mailTemplateService, SendererDtoConverter sendererDtoConverter, ReceiverDtoConverter receiverDtoConverter, MailTemplateDtoConverter mailTemplateDtoConverter, JavaMailSender javaMailSender) {
        this.receiverService = receiverService;
        this.sendererService = sendererService;
        this.mailTemplateService = mailTemplateService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID) {
        SendererDTO sendererDTO = sendererService.findById(senderID);
        Senderer senderer = new Senderer(sendererDTO.getId(), sendererDTO.getFname(), sendererDTO.getLname(), sendererDTO.getEmail());

        List<ReceiverDTO> receiverDTOS = receiverService.findAll();
        List<Receiver> receiverList = receiverDTOS.stream().map(receiverDTO -> new Receiver(receiverDTO.getId(), receiverDTO.getFname(), receiverDTO.getLname(), receiverDTO.getEmail(), receiverDTO.getGroupName())).toList();

        MailTemplateDTO mailTemplateDTO = mailTemplateService.findByID(mailTemplateID);
        MailTemplate mailTemplate = new MailTemplate(mailTemplateDTO.getId(), mailTemplateDTO.getHeader(), mailTemplateDTO.getBody());

        for (Receiver receiver : receiverList) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderer.getEmail());
            mailMessage.setTo(receiver.getEmail());
            mailMessage.setSubject(mailTemplate.getHeader());
            mailMessage.setText(mailTemplate.getBody());
            javaMailSender.send(mailMessage);
        }
    }
}
