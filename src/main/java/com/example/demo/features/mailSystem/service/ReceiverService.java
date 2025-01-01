package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReceiverService {
     void save(CreateReceiverRequest request);
     void deleteByID(Long id);
     ReceiverDTO findById(CreateReceiverRequest request);
     List<ReceiverDTO> findAll();
        void updateByID(Long id, CreateReceiverRequest request);
    }



