package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
}
