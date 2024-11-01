package com.example.demo.Entity;

import jakarta.persistence.*;

import javax.security.auth.Subject;
@Getter
@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="receiver")
    private String receiver;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;




}
