package com.example.demo.features.mailSystem.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateMailRequest extends BaseMailRequest{
    public CreateMailRequest(String header, String body) {
        super(header, body);
    }
}
