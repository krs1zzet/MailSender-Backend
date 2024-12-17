package com.example.demo.service;


import com.example.demo.dto.MailDTO;
import com.example.demo.dto.request.CreateMailRequest;

import java.util.List;

public interface MailService {
    List<MailDTO> findAll();
    MailDTO findByID(Long theID);
    void save(CreateMailRequest request);
    void deleteByID(Long id);
//    void assignSendererByMail(Long mailID, String sendererMail);
}
