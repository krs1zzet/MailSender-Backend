package com.example.demo.features.auth.service;


import com.example.demo.features.auth.dto.SignInRequestDTO;
import com.example.demo.features.auth.dto.SignInResponseDTO;
import com.example.demo.features.auth.dto.SignUpRequestDTO;
import com.example.demo.features.auth.dto.SignUpResponseDTO;

public interface AuthService {

  SignUpResponseDTO signUp(SignUpRequestDTO request);

  SignInResponseDTO signIn(SignInRequestDTO request);

   void signOut(String token);
}
