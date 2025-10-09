package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest extends BaseEventRequest{
    @NotNull(message = validationMessages.EVENT_NULL_PASSWORD)
    private String password;
    private Long ownerId;

}
