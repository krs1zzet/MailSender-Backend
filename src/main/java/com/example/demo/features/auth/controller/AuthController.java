package com.example.demo.features.auth.controller;


import com.example.demo.features.auth.dto.request.signInRequest;
import com.example.demo.features.auth.dto.request.signUpRequest;
import com.example.demo.features.auth.dto.signInDTO;
import com.example.demo.features.auth.dto.signUpDTO;
import com.example.demo.features.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${security.jwt.cookie-name:AUTH}")
    private String cookieName;

    @Value("${security.jwt.cookie-secure:false}")
    private boolean cookieSecure;

    @PostMapping("/signup")
    public ResponseEntity<signUpDTO> signUp(@RequestBody signUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<signInDTO> signIn(@RequestBody signInRequest request, HttpServletResponse resp) {
        signInDTO signInDTO = authService.signIn(request);
        ResponseCookie cookie= ResponseCookie.from(cookieName, signInDTO.getToken())
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/")
                .maxAge(Duration.ofMillis(signInDTO.getExpiresIn()))
                .sameSite("Lax")
                .build();
        resp.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return ResponseEntity.ok(signInDTO);
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signOut(HttpServletResponse res) {
        ResponseCookie cleared = ResponseCookie.from(cookieName, "")
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        res.addHeader(HttpHeaders.SET_COOKIE, cleared.toString());
        return ResponseEntity.ok().build();
    }





}
