package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseMailTemplateRequest {
    @NotNull(message = validationMessages.MAIL_TEMPLATE_NULL_HEADER)
    private String header;

    @NotNull(message = validationMessages.MAIL_TEMPLATE_NULL_BODY)
    private String body;

    private Long eventId;
}
