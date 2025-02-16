package com.example.demo.features.mailSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "receiver")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @NonNull
    @Column(name = "email")
    private String email;

    @Column(name = "group_name")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}
