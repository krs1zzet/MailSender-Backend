package com.example.demo.features.user.service;


import com.example.demo.features.user.dto.FullNameRequestDTO;
import com.example.demo.features.user.dto.UsagePurposeRequestDTO;
import com.example.demo.features.user.entity.UserEntity;
import com.example.demo.features.user.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

  UserEntity findByEmail(String email);

  UserEntity findById(Long id);

  void saveUser(UserEntity user);

  void updateFullName(FullNameRequestDTO fullName);

  boolean existsByEmail(String email);

  void updateUserPurpose(UsagePurposeRequestDTO userPurposeRequest);

  UserEntity findAuthenticatedUser();
}
