package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO {
    private long id;
    private long userId;
    private long eventId;
}
