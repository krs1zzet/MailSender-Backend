package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenderRepository extends JpaRepository<Sender,Long> {
    @Query("SELECT mt FROM Sender mt WHERE mt.event.id = :eventId")
    List<Sender> findByEventId(Long eventId);
}
