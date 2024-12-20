package com.example.demo.features.mailSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelReaderService {
    public void readRecipientsFromExcel(MultipartFile file) throws IOException;

    }



