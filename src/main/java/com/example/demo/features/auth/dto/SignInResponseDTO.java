package com.example.demo.features.auth.dto;

import com.example.demo.features.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SignInResponseDTO {

    final String token;

    final UserEntity user;
}
