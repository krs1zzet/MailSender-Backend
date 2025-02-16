package com.example.demo.features.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SignInRequestDTO {

    @NotEmpty
    final String email;

    @NotEmpty
    final String password;
}