package com.example.demo.service;

import com.example.demo.dto.ReceiverDTO;
import com.example.demo.dto.request.CreateMailRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ExcelReaderService {
    public void readRecipientsFromExcel(MultipartFile file) throws IOException;

    }



