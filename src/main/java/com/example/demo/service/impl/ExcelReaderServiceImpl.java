package com.example.demo.service.impl;

import com.example.demo.entity.Receiver;
import com.example.demo.dto.ReceiverDTO;
import com.example.demo.repo.ReceiverRepository;
import com.example.demo.service.ExcelReaderService;
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
public class ExcelReaderServiceImpl implements ExcelReaderService {
    private final ReceiverRepository receiverRepository;

    public ExcelReaderServiceImpl(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    @Override
    public void readRecipientsFromExcel(MultipartFile file) throws IOException {
        List<ReceiverDTO> recipients = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);  // İlk sayfa
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Başlık satırını atla

                // Hücrelerden verileri oku
                String name = row.getCell(0).getStringCellValue();
                String surname = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String groupName = row.getCell(3).getStringCellValue();

                // Convert DTO to entity and save
                Receiver receiver = new Receiver();
                receiver.setFname(name);
                receiver.setLname(surname);
                receiver.setEmail(email);
                receiver.setGroupName(groupName);
                receiverRepository.save(receiver);
            }
        }


    }
}
