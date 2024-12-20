package com.example.demo.features.user.service;

import com.example.demo.features.user.dto.SendererDTO;
import com.example.demo.features.user.dto.request.CreateSendererRequest;

import java.util.List;

public interface SendererService {
    SendererDTO findById(Long id);
    List<SendererDTO> findAll();
    void save(CreateSendererRequest request);
    void deleteById(Long id);
}
