package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.SenderDTO;
import com.example.demo.features.mailSystem.dto.request.CreateSenderRequest;

import java.util.List;

public interface SenderService {
    SenderDTO findById(Long id);
    List<SenderDTO> findAll();
    void save(CreateSenderRequest request);
    void deleteById(Long id);
    void updateById(Long id, CreateSenderRequest request);
}
