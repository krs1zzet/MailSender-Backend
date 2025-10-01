package com.example.demo.features.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class signInRequest {
    private String username;
    private String password;
}
