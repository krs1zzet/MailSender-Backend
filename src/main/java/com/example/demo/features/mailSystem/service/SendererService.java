package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.SendererDTO;
import com.example.demo.features.mailSystem.dto.request.CreateSendererRequest;

import java.util.List;

public interface SendererService {
    SendererDTO findById(Long id);
    List<SendererDTO> findAll();
    void save(CreateSendererRequest request);
    void deleteById(Long id);
    void updateById(Long id, CreateSendererRequest request);
}
