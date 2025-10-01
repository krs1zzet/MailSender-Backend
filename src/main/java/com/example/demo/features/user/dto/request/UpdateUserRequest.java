package com.example.demo.features.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest extends BaseUserRequest {
    private String username;
    private String password;
    private Long roleId;
}
