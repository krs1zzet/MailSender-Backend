package com.example.demo.features.mailSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    void saveReceiversFromExcel(MultipartFile file,Long eventId) throws IOException;
}
