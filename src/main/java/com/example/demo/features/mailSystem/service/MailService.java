package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.MailDTO;
import com.example.demo.features.mailSystem.entity.Receiver;

import java.util.List;

public interface MailService {
    MailDTO sendMail(Long senderID, List<Long> receiverIDs, Long mailTemplateID);;
}
