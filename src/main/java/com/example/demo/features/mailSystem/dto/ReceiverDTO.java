package com.example.demo.features.mailSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverDTO {
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String groupName;
}
