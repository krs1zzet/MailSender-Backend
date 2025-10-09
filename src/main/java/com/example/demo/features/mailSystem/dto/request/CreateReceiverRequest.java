package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateReceiverRequest extends BaseReceiverRequest {
    @NotBlank(message = validationMessages.RECEIVER_BLANK_EVENT_ID)
    private Long eventId;
}
