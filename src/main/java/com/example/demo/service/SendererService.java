package com.example.demo.service;

import com.example.demo.dto.SendererDTO;
import com.example.demo.dto.request.CreateSendererRequest;

import java.util.List;

public interface SendererService {
    SendererDTO findById(Long id);
    List<SendererDTO> findAll();
    void save(CreateSendererRequest request);
    void deleteById(Long id);
}
