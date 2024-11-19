package com.example.demo.dto.converter;

import com.example.demo.Entity.Senderer;
import com.example.demo.dto.SendererDTO;

import java.util.List;

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
