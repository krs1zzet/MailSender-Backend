package com.example.demo.features.auth.service.Impl;

import com.example.demo.features.auth.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expiryMs;

    private SecretKey key;

    @PostConstruct
    void init() {
        // secret en az ~32 byte olmalı
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public String generate(String subject) {
        return generate(subject, Map.of());
    }

    @Override
    public String generate(String subject, Map<String, Object> claims) {
        Instant exp = Instant.now().plusMillis(expiryMs);
        return buildToken(subject,claims,exp);
    }

    @Override
    public String generateUntil(String subject, Map<String, Object> claims, Instant expiresAt) {
        return buildToken(subject,claims,expiresAt);
    }

    private String buildToken(String subject, Map<String, Object> claims, Instant exp) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims == null ? Map.of() : claims)
                .issuedAt(new Date())
                .expiration(Date.from(exp))
                .signWith(key)
                .compact();
    }

    @Override
    public Optional<String> getSubject(String token) {
        return parseClaims(token).map(Claims::getSubject);
    }

    @Override
    public Optional<Instant> getExpiration(String token) {
        return parseClaims(token).map(c -> c.getExpiration().toInstant());
    }
    @Override
    public boolean isValid(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            var exp = claims.getExpiration();
            return exp != null && exp.toInstant().isAfter(Instant.now());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Optional<Claims> parseClaims(String token){
        try{
            return Optional.of(
                    Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
            );
        }catch (JwtException | IllegalArgumentException e){
            return Optional.empty();
        }
    }
}
