package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private Instant date;
}
