package com.example.demo.features.user.dto.converter;

import com.example.demo.features.mailSystem.entity.Mail;
import com.example.demo.features.user.entity.Senderer;
import com.example.demo.features.user.dto.SendererDTO;
import org.springframework.stereotype.Component;

import java.util.List;

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
