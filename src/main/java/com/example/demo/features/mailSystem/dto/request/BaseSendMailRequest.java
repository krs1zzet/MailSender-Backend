package com.example.demo.features.mailSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSendMailRequest {
    private Long senderId;
    private Long MailTemplateId;
    private List<Long> receiverIds;
}
