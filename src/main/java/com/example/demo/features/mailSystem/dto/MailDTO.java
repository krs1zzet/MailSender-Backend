package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class MailDTO {
    private boolean success;
    private List<String> failedEmails;
    private String message;



}
