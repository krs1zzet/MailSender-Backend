package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.entity.Mail;
import com.example.demo.features.mailSystem.entity.Receiver;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceiverDtoConverter {
    public ReceiverDTO convert (Receiver from){
        List<Long> mailIds = from.getMails().stream().map(Mail::getId).toList();
        return new ReceiverDTO(
                from.getId(),
                from.getFname(),
                from.getLname(),
                from.getEmail(),
                from.getGroupName(),
                mailIds
        );

    }
    public List<ReceiverDTO> convert(List<Receiver> from){
        return from.stream().map(this::convert).toList();
    }
}
