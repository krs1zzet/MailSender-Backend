package com.example.demo.dto.converter;

import com.example.demo.Entity.Receiver;
import com.example.demo.dto.ReceiverDTO;

import java.util.List;

public class ReceiverDtoConverter {
    public ReceiverDTO convert (Receiver from){
        return new ReceiverDTO(
                from.getId(),
                from.getFname(),
                from.getLname(),
                from.getEmail()
        );

    }
    public List<ReceiverDTO> convert(List<Receiver> from){
        return from.stream().map(this::convert).toList();
    }
}
