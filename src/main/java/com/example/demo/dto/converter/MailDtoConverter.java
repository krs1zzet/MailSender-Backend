package com.example.demo.dto.converter;

import com.example.demo.Entity.Mail;
import com.example.demo.dto.MailDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MailDtoConverter {
    public MailDTO convert (Mail from){
        return new MailDTO(
                from.getId(),
                from.getHeader(),
                from.getBody(),
                from.getSenderer().getId()
        );
    }

    public List<MailDTO> convert (List<Mail> from){
        return from.stream().map(this::convert).toList();
    }

}


