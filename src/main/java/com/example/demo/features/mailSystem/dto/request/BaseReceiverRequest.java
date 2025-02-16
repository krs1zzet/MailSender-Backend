package com.example.demo.features.mailSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseReceiverRequest {
    private String fname;
    private String lname;
    private String email;
    private String groupName;
    private Long eventId;
}
