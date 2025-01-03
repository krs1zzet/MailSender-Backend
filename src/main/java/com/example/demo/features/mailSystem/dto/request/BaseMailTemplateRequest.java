package com.example.demo.features.mailSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseMailTemplateRequest {
    private String header;
    private String body;
    private Long eventId;
}
