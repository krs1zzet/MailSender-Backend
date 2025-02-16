package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.entity.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDtoConverter {
    public EventDTO convert(Event from) {
        return new EventDTO(
                from.getId(),
                from.getName(),
                from.getDescription(),
                from.getDate()
        );
    }

    public List<EventDTO> convert(List<Event> from) {
        return from.stream().map(this::convert).toList();
    }

}
