package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Senderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendererRepository extends JpaRepository<Senderer,Long> {
}
