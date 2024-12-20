package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.entity.Mail;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.dto.MailDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailDtoConverter {
    public MailDTO convert(Mail from) {
        List<Long> receiverIds = from.getReceivers().stream()
                .map(Receiver::getId)
                .collect(Collectors.toList());

        return new MailDTO(
                from.getId(),
                from.getHeader(),
                from.getBody(),
                from.getSenderer() != null ? from.getSenderer().getId() : null,
                receiverIds
        );
    }

    public List<MailDTO> convert(List<Mail> from) {
        return from.stream().map(this::convert).toList();
    }
}