package com.example.demo.features.mailSystem.entity;

import jakarta.persistence.*;

@Entity
public class EventDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    @ManyToOne
    @JoinColumn(name = "MailTemplate_id")
    private MailTemplate mailTemplate;

    @ManyToOne
    @JoinColumn(name = "Sender_id")
    private Sender sender;
}
