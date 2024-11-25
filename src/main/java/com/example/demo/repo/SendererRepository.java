package com.example.demo.repo;

import com.example.demo.Entity.Mail;
import com.example.demo.Entity.Senderer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SendererRepository extends JpaRepository<Senderer,Integer> {
    Optional<Mail> findByEmail(String email);

}
