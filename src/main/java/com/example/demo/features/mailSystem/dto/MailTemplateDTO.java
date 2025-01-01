package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailTemplateDTO {
    private Long id;
    private String header;
    private String body;
}
