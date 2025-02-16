package com.example.demo.features.auth.dto;

import com.example.demo.features.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponseDTO {
  private final String token;
  private final UserEntity user;
}
