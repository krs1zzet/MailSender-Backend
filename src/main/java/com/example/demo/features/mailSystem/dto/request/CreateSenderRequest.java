package com.example.demo.features.mailSystem.dto.request;

import lombok.*;

@Getter
@Setter
public class CreateSenderRequest extends BaseSenderRequest {
    private String password;
}
