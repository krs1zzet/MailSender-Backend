package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate,Long> {
    @Query("SELECT mt FROM MailTemplate mt WHERE mt.event.id = :eventId")
    List<MailTemplate> findByEventId(Long eventId);
}
