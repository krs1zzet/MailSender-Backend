package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate,Long> {

}
