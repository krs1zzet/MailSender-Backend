package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import com.example.demo.features.mailSystem.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver,Long> {
    @Query("SELECT mt FROM Receiver mt WHERE mt.event.id = :eventId")
    List<Receiver> findByEventId(Long eventId);

}
