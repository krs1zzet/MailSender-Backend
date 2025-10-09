package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.dto.request.BaseReceiverRequest;
import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReceiverService {
     void save(CreateReceiverRequest request);
     void deleteByID(Long id);
     ReceiverDTO findById(Long id);
     List<ReceiverDTO> findAll();
    ReceiverDTO updateByID(Long id, BaseReceiverRequest request);
     List<ReceiverDTO> findReceiversByEventId(Long theID);
      ReceiverDTO findByEmail(String email);
    }



