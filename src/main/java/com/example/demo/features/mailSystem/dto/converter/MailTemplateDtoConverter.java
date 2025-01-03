package com.example.demo.features.mailSystem.dto.converter;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.dto.MailTemplateDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MailTemplateDtoConverter {
    public MailTemplateDTO convert(MailTemplate from) {

        return new MailTemplateDTO(
                from.getId(),
                from.getHeader(),
                from.getBody(),
                from.getEvent().getId()
        );
    }

    public List<MailTemplateDTO> convert(List<MailTemplate> from) {
        return from.stream().map(this::convert).toList();
    }
}