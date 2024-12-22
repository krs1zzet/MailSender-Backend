package com.example.demo.features.mailSystem.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateReceiverRequest extends BaseReceiverRequest {
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String groupName;
}
