package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.repository.ReceiverRepository;
import com.example.demo.features.mailSystem.service.EventService;
import com.example.demo.features.mailSystem.service.ExcelService;
import com.example.demo.features.mailSystem.service.ReceiverService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    private final ReceiverRepository receiverRepository;
    private final EventService eventService;
    private final ReceiverService receiverService;

    public ExcelServiceImpl(ReceiverRepository receiverRepository, EventService eventService, ReceiverService receiverService) {
        this.receiverRepository = receiverRepository;
        this.eventService = eventService;
        this.receiverService = receiverService;
    }

    @Transactional
    @Override
    public void saveReceiversFromExcel(MultipartFile file, Long eventId) throws IOException {
        List<CreateReceiverRequest> receivers = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                CreateReceiverRequest receiverRequest = new CreateReceiverRequest();
                receiverRequest.setFname(row.getCell(0).getStringCellValue());
                receiverRequest.setLname(row.getCell(1).getStringCellValue());
                receiverRequest.setGroupName(row.getCell(2).getStringCellValue());
                receiverRequest.setEmail(row.getCell(3).getStringCellValue());
                receiverRequest.setEventId(eventId);
                receivers.add(receiverRequest);
            }
        }
        for (CreateReceiverRequest receiver : receivers) {
            receiverService.save(receiver);
        }

    }
}
