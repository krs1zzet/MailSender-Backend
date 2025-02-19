package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseSenderRequest {
    @NotBlank(message = validationMessages.SENDER_BLANK_EMAIL)
    @Email(message = validationMessages.SENDER_INVALID_EMAIL)
    private String email;
    private Long eventId;
}
