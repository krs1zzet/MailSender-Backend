package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.dto.ParticipationDTO;
import com.example.demo.features.mailSystem.dto.ReceiverDTO;
import com.example.demo.features.mailSystem.entity.Participation;
import com.example.demo.features.mailSystem.entity.Receiver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipationDtoConverter {
    public ParticipationDTO convert(Participation from) {
        return new ParticipationDTO(
                from.getId(),
                from.getUser().getId(),
                from.getEvent().getId()
        );
    }
    public List<ParticipationDTO> convert(List<Participation> from) {
        return from.stream().map(this::convert).toList();
    }
}
