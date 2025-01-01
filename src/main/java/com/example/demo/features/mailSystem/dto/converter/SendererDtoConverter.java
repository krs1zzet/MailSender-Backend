package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.entity.Senderer;
import com.example.demo.features.mailSystem.dto.SendererDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendererDtoConverter {
    public SendererDTO convert (Senderer from){
        return new SendererDTO(
                from.getId(),
                from.getFname(),
                from.getLname(),
                from.getEmail()
        );
    }
    public List<SendererDTO> convert (List<Senderer> from){
        return from.stream().map(this::convert).toList();
    }
}
