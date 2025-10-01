package com.example.demo.features.mailSystem.service;

import com.example.demo.features.mailSystem.dto.EventDTO;
import com.example.demo.features.mailSystem.dto.ParticipationDTO;
import com.example.demo.features.mailSystem.entity.Event;
import com.example.demo.features.mailSystem.entity.Participation;
import com.example.demo.features.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ParticipationService {
    void save(User user, Event event);
    void deleteByID(long id);
    List<ParticipationDTO> findAll();
    ParticipationDTO findByID(long id);
    List<Long> findEventIdsByUserId(Long userId);
}
