package com.example.demo.features.auth.service;


import com.example.demo.features.auth.dto.SignInRequestDTO;
import com.example.demo.features.auth.dto.SignInResponseDTO;
import com.example.demo.features.auth.dto.SignUpRequestDTO;
import com.example.demo.features.auth.dto.SignUpResponseDTO;
import com.example.demo.features.user.entity.UserEntity;
import com.example.demo.features.user.enums.Roles;
import com.example.demo.features.user.enums.UsagePurpose;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.exceptions.user.EmailAlreadyExistsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder,
      JwtService jwtService, AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public SignUpResponseDTO signUp(SignUpRequestDTO request) {

    if (userService.existsByEmail(request.getEmail())) {
      throw new EmailAlreadyExistsException("This email is already in use.");
    }
    Timestamp currentTimestamp = Timestamp.from(Instant.now());
    UsagePurpose usagePurpose = UsagePurpose.fromString(request.getPurpose());

    UserEntity user = UserEntity.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .username("")
        .usagePurpose(usagePurpose)
        .fullName(request.getFullName())
        .createdAt(currentTimestamp)
        .updatedAt(currentTimestamp)
        .role(Roles.SIGNED)
        .build();

    userService.saveUser(user);

    String jwtToken = jwtService.generateToken(user);

    return SignUpResponseDTO.builder().token(jwtToken).user(user).build();
  }

  public SignInResponseDTO signIn(SignInRequestDTO request) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    } catch (Exception e) {
      throw new AccessDeniedException("Invalid email or password.");
    }

    UserEntity user = userService.findByEmail(request.getEmail());

    String jwtToken = jwtService.generateToken(user);

    return SignInResponseDTO.builder()
        .token(jwtToken)
        .user(user)
        .build();
  }

}