package com.example.demo.features.auth.service;


import com.example.demo.features.auth.dto.request.signInRequest;
import com.example.demo.features.auth.dto.request.signUpRequest;
import com.example.demo.features.auth.dto.signInDTO;
import com.example.demo.features.auth.dto.signUpDTO;

public interface AuthService {
    signUpDTO signUp(signUpRequest request);
    signInDTO signIn(signInRequest request);
}
