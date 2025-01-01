package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderRepository extends JpaRepository<Sender,Long> {
}
