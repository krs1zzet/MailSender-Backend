package com.example.demo.service;

import com.example.demo.Entity.Mail;
import com.example.demo.repo.MailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl {
    private final MailRepository mailRepository;

    public MailServiceImpl(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }


    public List<Mail> findAll() {
        return mailRepository.findAll();

    }
    public void findById(Long id) {
        mailRepository.findById(id);
    }
    public void save(Mail mail) {
        mailRepository.save(mail);
    }
    public void DeleteById(Long id) {
        mailRepository.deleteById(id);
    }
}
