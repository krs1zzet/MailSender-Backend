package com.example.demo.dto.converter;

import com.example.demo.Entity.Mail;
import com.example.demo.Entity.Receiver;
import com.example.demo.dto.ReceiverDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
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
