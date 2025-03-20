package com.example.demo.features.auth.service;

import com.example.demo.features.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {

  String extractUsername(String token);

  <T> T extractClaim(
      String token,
      Function<Claims, T> claimsResolver);

  String generateToken(UserEntity user);

  String generateToken(
      Map<String, Object> extraClaims,
      UserEntity user);

  boolean isTokenValid(
      String token,
      UserDetails userDetails);

    boolean isTokenExpired(String token);
    Date extractExpiration(String token);


}
