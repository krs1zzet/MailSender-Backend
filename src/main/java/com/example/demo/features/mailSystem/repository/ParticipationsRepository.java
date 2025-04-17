package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Event;
import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationsRepository extends JpaRepository<Participation,Long> {
    @Query("SELECT p.event.id FROM Participation p WHERE p.user.id = :userId")
    List<Long> findEventIdsByUserId(@Param("userId") Long userId);

}
