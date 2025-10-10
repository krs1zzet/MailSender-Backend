package com.example.demo.product.config;

import com.example.demo.features.auth.service.JwtService;
import com.example.demo.features.user.service.UserService;
import com.example.demo.product.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${security.jwt.cookie-name}")
    private String cookieName;

    /**
     * Prod ve local origin'leri (şema dahil) — HTTPS prod eklendi.
     * Not: Tam eşleşme için setAllowedOrigins; wildcard için setAllowedOriginPatterns kullanılır.
     */
    private static final List<String> ALLOWED_ORIGINS = List.of(
            "https://mailsender.izzettin.dev",
            "http://localhost:5173",
            "http://localhost:5174",
            "http://127.0.0.1:5173",
            "https://localhost:5173",
            "https://localhost:5174",
            "https://127.0.0.1:5173"
    );

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();

        // Tam eşleşme: listedeki origin'ler
        conf.setAllowedOrigins(ALLOWED_ORIGINS);

        // Eğer *.izzettin.dev gibi wildcard da istiyorsan aşağıyı aç ve üstteki satırı yoruma al:
        // conf.setAllowedOriginPatterns(List.of("https://*.izzettin.dev", "http://localhost:*", "https://localhost:*"));

        conf.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        conf.setAllowedHeaders(List.of("*")); // istersen "Authorization","Content-Type" vb. olarak daralt
        conf.setAllowCredentials(true);       // cookie/JWT-cookie ile auth yapıyorsan gerekli

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthenticationFilter(jwtService, userService, cookieName);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Preflight serbest
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Public endpoint'ler
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/role/**",

                                // Actuator health'ı public tutmak istiyorsan (UI'dan çağırmayacaksan bile curl için pratik)
                                "/actuator/health",
                                "/actuator/health/**"

                                // Eğer actuator'ı /api/actuator altına taşırsan şunları da ekleyebilirsin:
                                // "/api/actuator/health",
                                // "/api/actuator/health/**"
                        ).permitAll()

                        // Geri kalan her şey auth ister
                        .anyRequest().authenticated()
                )
                // JWT filtresi: UsernamePasswordAuthenticationFilter'dan önce
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
