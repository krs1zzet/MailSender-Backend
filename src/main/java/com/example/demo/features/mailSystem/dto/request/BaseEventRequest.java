package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEventRequest {
    @NotNull( message= validationMessages.EVENT_NULL_NAME)
    private String name;

    private String description;

    private Instant date;
}
