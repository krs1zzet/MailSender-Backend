package com.example.demo.features.mailSystem.dto.request;
import com.example.demo.features.mailSystem.constants.validationMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseParticipationRequest {
    @NotBlank(message = validationMessages.USER_BLANK_ID)
    Long userId;
    @NotBlank(message = validationMessages.EVENT_BLANK_ID)
    Long eventId;
}
