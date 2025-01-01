package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.request.CreateReceiverRequest;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.repository.ReceiverRepository;
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

import static org.apache.xmlbeans.impl.store.Public2.save;
@Service
public class ExcelServiceImpl implements ExcelService {

    private final ReceiverRepository receiverRepository;

    public ExcelServiceImpl(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    @Transactional
    @Override
    public void saveReceiversFromExcel(MultipartFile file) throws IOException {
        List<Receiver> receivers = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Receiver receiver = new Receiver();
                receiver.setFname(row.getCell(0).getStringCellValue());
                receiver.setLname(row.getCell(1).getStringCellValue());
                receiver.setGroupName(row.getCell(2).getStringCellValue());
                receiver.setEmail(row.getCell(3).getStringCellValue());
                receivers.add(receiver);
            }
        }
        receiverRepository.saveAll(receivers);
    }
}
