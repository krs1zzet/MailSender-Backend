package com.example.demo.controller;

import com.example.demo.dto.ReceiverDTO;
import com.example.demo.service.ExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReceiverController {

    private final ExcelReaderService excelReaderService;

    @Autowired
    public ReceiverController(ExcelReaderService excelReaderService) {
        this.excelReaderService = excelReaderService;
    }

    @PostMapping("/read-receivers")
    public void readReceivers(@RequestParam("file") MultipartFile file) {
        try {
            excelReaderService.readRecipientsFromExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            List.of();
        }
    }
}