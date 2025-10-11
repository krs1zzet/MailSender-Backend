package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.product.exceptions.errorMessages.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.example.demo.features.mailSystem.constants.validationMessages;

@Getter
@Setter
public class CreateSenderRequest extends BaseSenderRequest {
    private Long eventId;


}
