package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail,Long> {
    @Query("SELECT m FROM Mail m WHERE m.id = ?1")
    public Mail findMailById(Long id);

}
