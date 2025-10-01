package com.example.demo.features.mailSystem.service.impl;

import com.example.demo.features.mailSystem.dto.ParticipationDTO;
import com.example.demo.features.mailSystem.dto.converter.ParticipationDtoConverter;
import com.example.demo.features.mailSystem.entity.Event;
import com.example.demo.features.mailSystem.entity.Participation;
import com.example.demo.features.mailSystem.repository.ParticipationsRepository;
import com.example.demo.features.mailSystem.service.ParticipationService;
import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.exceptions.generic.participationExceptions.NotFoundExceptions.ParticipationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationsRepository participationsRepository;
    private final ParticipationDtoConverter participationDtoConverter;

    public ParticipationServiceImpl(ParticipationsRepository participationsRepository,
                                    UserService userService,
                                    ParticipationDtoConverter participationDtoConverter) {
        this.participationsRepository = participationsRepository;
        this.participationDtoConverter = participationDtoConverter;
    }


    @Override
    public void save(User user, Event event)
    {
        Participation participation = new Participation();
        participation.setUser(user);
        participation.setEvent(event);
        participationsRepository.save(participation);
    }



    @Override
    public void deleteByID(long id) {
        Optional<Participation> participation = participationsRepository.findById(id);
        Participation theParticipation = participation.orElseThrow(() -> new ParticipationNotFoundException(id));
        participationsRepository.deleteById(theParticipation.getId());
    }

    @Override
    public List<ParticipationDTO> findAll() {
        List<Participation> theParticipations = participationsRepository.findAll();
        return participationDtoConverter.convert(theParticipations);
    }

    @Override
    public ParticipationDTO findByID(long id) {
        Optional<Participation> participation = participationsRepository.findById(id);
        Participation theParticipation = participation.orElseThrow(() -> new ParticipationNotFoundException(id));
        return participationDtoConverter.convert(theParticipation);
    }

    @Override
    public List<Long> findEventIdsByUserId(Long userId){
        return participationsRepository.findEventIdsByUserId(userId);
    }


}
