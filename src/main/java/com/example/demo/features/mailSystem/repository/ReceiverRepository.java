package com.example.demo.features.mailSystem.repository;

import com.example.demo.features.mailSystem.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver,Long> {

}
