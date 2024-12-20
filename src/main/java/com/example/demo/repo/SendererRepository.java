package com.example.demo.repo;

import com.example.demo.Entity.Mail;
import com.example.demo.Entity.Senderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SendererRepository extends JpaRepository<Senderer,Long> {
    Optional<Mail> findByEmail(String email);

}
