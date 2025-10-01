package com.example.demo.features.auth.service;


import java.time.Instant;
import java.util.Map;
import java.util.Optional;

public interface JwtService {

    String generate(String subject);
    String generate(String subject, Map<String,Object> claims);

    String generateUntil(String subject, Map<String,Object> claims, Instant expiresAt);

    Optional<String> getSubject(String token);
    Optional<Instant> getExpiration(String token);
    boolean isValid(String token);


}
