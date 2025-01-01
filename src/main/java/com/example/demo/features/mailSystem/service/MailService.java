package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.entity.Receiver;

import java.util.List;

public interface MailService {
    void sendMail(Long senderID, List<Long> receiverIDs,Long mailTemplateID);;
}
