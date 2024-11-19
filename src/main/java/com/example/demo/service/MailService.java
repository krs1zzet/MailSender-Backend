package com.example.demo.service;


import com.example.demo.Entity.Mail;
import com.example.demo.dto.MailDTO;

import java.util.List;

public interface MailService {
    List<MailDTO> findAll();
}
