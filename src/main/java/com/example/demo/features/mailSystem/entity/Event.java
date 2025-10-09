package com.example.demo.features.mailSystem.entity;

import com.example.demo.features.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Instant date;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MailTemplate> mailTemplates;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Receiver> receivers;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sender> senders;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Participation> participations;



}
