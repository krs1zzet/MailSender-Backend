package com.example.demo.features.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseSendererRequest {
    private String fname;
    private String lname;
    private String email;
}
