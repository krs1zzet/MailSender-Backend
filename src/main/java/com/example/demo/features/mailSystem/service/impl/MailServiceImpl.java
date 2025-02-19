package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.MailDTO;
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
import com.example.demo.product.exceptions.generic.mailTemplateExceptions.NotFoundExceptions.MailTemplateIdNotFoundException;
import com.example.demo.product.exceptions.generic.receiverExceptions.NotFoundExceptions.ReceiverListIdNotFoundException;
import com.example.demo.product.exceptions.generic.senderExceptions.BadRequestExceptions.SenderInvalidPasswordException;
import com.example.demo.product.exceptions.generic.senderExceptions.BadRequestExceptions.SenderNullPasswordException;
import com.example.demo.product.exceptions.generic.senderExceptions.NotFoundExceptions.SenderIdNotFoundException;
import jakarta.mail.AuthenticationFailedException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public MailDTO sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID) {

        SenderDTO senderDTO = senderService.findById(senderID);

        if (senderDTO == null) {
            throw new SenderIdNotFoundException(senderID);
        }

        Sender sender = new Sender(senderDTO.getId(), senderDTO.getEmail(), senderDTO.getCreatedAt(), senderDTO.getLastUsedAt(), eventService.findById_ReturnEvent(senderDTO.getEventId()));


        List<ReceiverDTO> receiverDTOS = receiverIDs.stream()
                .map(receiverService::findById)
                .filter(Objects::nonNull)
                .toList();

// 🔹 Find missing IDs by filtering out those that were successfully retrieved
        List<Long> missingReceiverIDs = receiverIDs.stream()
                .filter(id -> receiverDTOS.stream().noneMatch(receiver -> receiver.getId().equals(id)))
                .toList();

        if (!missingReceiverIDs.isEmpty()) {
            throw new ReceiverListIdNotFoundException(missingReceiverIDs); // ✅ Only pass missing IDs
        }



        List<Receiver> receiverList = receiverDTOS.stream().map(receiverDTO -> new Receiver(receiverDTO.getId(), receiverDTO.getFname(), receiverDTO.getLname(), receiverDTO.getEmail(), receiverDTO.getGroupName(), eventService.findById_ReturnEvent(receiverDTO.getEventId()))).toList();

        MailTemplateDTO mailTemplateDTO = mailTemplateService.findByID(mailTemplateID);
        if(mailTemplateDTO == null){
            throw new MailTemplateIdNotFoundException(mailTemplateID);
        }
        MailTemplate mailTemplate = new MailTemplate(mailTemplateDTO.getId(), mailTemplateDTO.getHeader(), mailTemplateDTO.getBody(), eventService.findById_ReturnEvent(mailTemplateDTO.getEventId()));

        List<String> failedEmails = new ArrayList<>();

        for (Receiver receiver : receiverList) {
            try {
                JavaMailSenderImpl mailSender = createJavaMailSender(senderDTO.getEmail(), senderService.findPasswordBySenderId(senderID));
                if(mailSender.getPassword() == null || mailSender.getPassword().isEmpty()){
                    throw new SenderNullPasswordException(senderID);
                }
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender.getEmail());
                mailMessage.setTo(receiver.getEmail());
                mailMessage.setSubject(mailTemplate.getHeader());
                mailMessage.setText(mailTemplate.getBody());
                mailSender.send(mailMessage);
                System.out.println("Mail sent to: " + receiver.getEmail());

            } catch (MailAuthenticationException  e) { // 🔹 Catch authentication errors
                throw new SenderInvalidPasswordException(senderID);

            } catch (SenderNullPasswordException e){
                throw e;
            }
            catch (Exception e) {
                System.err.println("Failed to send mail to: " + receiver.getEmail() + ". Error: " + e.getMessage());
                failedEmails.add(receiver.getEmail());
            }
        }
        if (!failedEmails.isEmpty()) {
            return new MailDTO(false, failedEmails, "Failed to send mail to some recipients.");
        }

        return new MailDTO(true, Collections.emptyList(), "All mails sent successfully.");
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