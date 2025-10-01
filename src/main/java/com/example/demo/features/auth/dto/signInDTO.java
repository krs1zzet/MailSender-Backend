package com.example.demo.features.auth.dto;


import com.example.demo.features.user.dto.UserDTO;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@Data
public class signInDTO {
    private Long id;
    private String token;
    private UserDTO user;
    private long expiresIn;
}
