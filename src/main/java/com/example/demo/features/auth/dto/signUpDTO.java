package com.example.demo.features.auth.dto;

import com.example.demo.features.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class signUpDTO {
    private Long id;
    private UserDTO user;
}
