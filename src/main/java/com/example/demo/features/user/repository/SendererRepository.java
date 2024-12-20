package com.example.demo.features.user.repository;

import com.example.demo.features.mailSystem.entity.Mail;
import com.example.demo.features.user.entity.Senderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SendererRepository extends JpaRepository<Senderer,Long> {
    Optional<Mail> findByEmail(String email);

}
