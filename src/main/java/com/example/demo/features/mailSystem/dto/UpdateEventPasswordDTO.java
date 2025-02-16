package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateEventPasswordDTO {
    private Long eventId;
    private String newPassword;
}
