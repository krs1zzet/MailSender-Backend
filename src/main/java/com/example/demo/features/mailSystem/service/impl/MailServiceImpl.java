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
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private final ReceiverService receiverService;
    private final SenderService senderService;
    private final MailTemplateService mailTemplateService;
    private final EventService eventService;

    public MailServiceImpl(ReceiverService receiverService, SenderService senderService, MailTemplateService mailTemplateService, SenderDtoConverter senderDtoConverter, ReceiverDtoConverter receiverDtoConverter, MailTemplateDtoConverter mailTemplateDtoConverter,  EventService eventService) {
        this.receiverService = receiverService;
        this.senderService = senderService;
        this.mailTemplateService = mailTemplateService;
        this.eventService = eventService;
    }

    @Override
    public void sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID) {

        SenderDTO senderDTO = senderService.findById(senderID);
        Sender sender = new Sender(senderDTO.getId(), senderDTO.getEmail(), senderDTO.getCreatedAt(), senderDTO.getLastUsedAt(), eventService.findById_ReturnEvent(senderDTO.getEventId()));

        List<ReceiverDTO> receiverDTOS = receiverService.findAll();
        List<Receiver> receiverList = receiverDTOS.stream().map(receiverDTO -> new Receiver(receiverDTO.getId(), receiverDTO.getFname(), receiverDTO.getLname(), receiverDTO.getEmail(), receiverDTO.getGroupName(), eventService.findById_ReturnEvent(receiverDTO.getEventId()))).toList();

        MailTemplateDTO mailTemplateDTO = mailTemplateService.findByID(mailTemplateID);
        MailTemplate mailTemplate = new MailTemplate(mailTemplateDTO.getId(), mailTemplateDTO.getHeader(), mailTemplateDTO.getBody(), eventService.findById_ReturnEvent(mailTemplateDTO.getEventId()));

        for (Receiver receiver : receiverList) {
            try {
                JavaMailSenderImpl mailSender = createJavaMailSender(senderDTO.getEmail(), senderService.findPasswordBySenderId(senderID));
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender.getEmail());
                mailMessage.setTo(receiver.getEmail());
                mailMessage.setSubject(mailTemplate.getHeader());
                mailMessage.setText(mailTemplate.getBody());
                mailSender.send(mailMessage);
                System.out.println("Mail sent to: " + receiver.getEmail());
            } catch (Exception e) {
                System.err.println("Failed to send mail to: " + receiver.getEmail() + ". Error: " + e.getMessage());
            }
        }
    }

    private JavaMailSenderImpl createJavaMailSender(String email, String appPassword) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(email); // Sender email
        mailSender.setPassword(appPassword); // App password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;

    }
}