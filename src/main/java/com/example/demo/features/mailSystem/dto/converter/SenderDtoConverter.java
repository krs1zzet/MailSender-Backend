package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.entity.Sender;
import com.example.demo.features.mailSystem.dto.SenderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenderDtoConverter {
    public SenderDTO convert (Sender from){
        return new SenderDTO(
                from.getId(),
                from.getEmail(),
                from.getCreatedAt(),
                from.getLastUsedAt(),
                from.getEvent().getId()
        );
    }
    public List<SenderDTO> convert (List<Sender> from){
        return from.stream().map(this::convert).toList();
    }
}
