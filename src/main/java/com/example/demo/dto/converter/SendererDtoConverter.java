package com.example.demo.dto.converter;

import com.example.demo.Entity.Mail;
import com.example.demo.Entity.Senderer;
import com.example.demo.dto.SendererDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class SendererDtoConverter {
    public SendererDTO convert (Senderer from){
        List<Long>mailIds = from.getMails().stream().map(Mail::getId).toList();
        return new SendererDTO(
                from.getId(),
                from.getFname(),
                from.getLname(),
                from.getEmail(),
                mailIds
        );
    }
    public List<SendererDTO> convert (List<Senderer> from){
        return from.stream().map(this::convert).toList();
    }
}
