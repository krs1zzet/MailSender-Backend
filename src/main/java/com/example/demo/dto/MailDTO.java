package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    private Long id;
    private String header;
    private String body;
    private Long sender_id;
    private List<Long> receiver_ids;
}
