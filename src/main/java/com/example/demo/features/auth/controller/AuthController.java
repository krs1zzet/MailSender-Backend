package com.example.demo.features.auth.controller;

import com.example.demo.features.auth.dto.SignInRequestDTO;
import com.example.demo.features.auth.dto.SignInResponseDTO;
import com.example.demo.features.auth.dto.SignUpRequestDTO;
import com.example.demo.features.auth.dto.SignUpResponseDTO;
import com.example.demo.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDTO> signUp(
            @Valid
            @RequestBody
            SignUpRequestDTO request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDTO> signIn(
            @Valid
            @RequestBody
            SignInRequestDTO request) {
        return ResponseEntity.ok(authService.signIn(request));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        // Trim the token to remove any unwanted spaces or newlines
        token = token.trim();

        authService.signOut(token);
        return ResponseEntity.ok().build();
    }


}
