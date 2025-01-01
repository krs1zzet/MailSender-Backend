package com.example.demo.features.mailSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetails extends JpaRepository<EventDetails,Long> {
}
