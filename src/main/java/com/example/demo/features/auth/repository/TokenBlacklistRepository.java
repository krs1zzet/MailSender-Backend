package com.example.demo.features.auth.repository;

import com.example.demo.features.auth.entity.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<BlacklistedToken,String> {
    boolean existsByToken(String token);
}
