package com.example.demo.features.mailSystem.service;


import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.request.CreateMailTemplateRequest;

import java.util.List;

public interface MailTemplateService {
    List<MailTemplateDTO> findAll();
    MailTemplateDTO findByID(Long theID);
    void save(CreateMailTemplateRequest request);
    void deleteByID(Long id);
    void updateByID(Long id, CreateMailTemplateRequest request);
}
