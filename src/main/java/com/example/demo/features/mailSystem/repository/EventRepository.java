package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
