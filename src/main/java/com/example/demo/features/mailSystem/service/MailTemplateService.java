package com.example.demo.features.mailSystem.service;


import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import com.example.demo.features.mailSystem.dto.request.CreateMailTemplateRequest;
import com.example.demo.features.mailSystem.dto.request.UpdateMailTemplateRequest;

import java.util.List;

public interface MailTemplateService {
    List<MailTemplateDTO> findAll();
    MailTemplateDTO findByID(Long theID);
    List<MailTemplateDTO> findMailTemplatesByEventId(Long theID);
    void save(CreateMailTemplateRequest request);
    void deleteByID(Long id);
    MailTemplateDTO updateByID(Long id, UpdateMailTemplateRequest request);
}
