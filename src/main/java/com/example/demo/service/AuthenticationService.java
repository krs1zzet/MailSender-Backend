package com.example.demo.service;

import com.example.demo.payload.request.AuthenticationRequest;
import com.example.demo.payload.request.RegisterRequest;
import com.example.demo.payload.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register (RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
