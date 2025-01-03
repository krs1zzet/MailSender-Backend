package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenderDTO {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime lastUsedAt;
    private Long eventId;
}
