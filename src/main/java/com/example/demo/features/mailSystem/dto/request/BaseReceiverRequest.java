package com.example.demo.features.mailSystem.dto.request;

import com.example.demo.features.mailSystem.constants.validationMessages;
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
public class BaseReceiverRequest {
    private String fname;
    private String lname;

    @NotBlank(message = validationMessages.RECEIVER_BLANK_EMAIL)
    @Email(message = validationMessages.RECEIVER_INVALID_EMAIL)
    private String email;

    private String groupName;
    private Long eventId;
}
