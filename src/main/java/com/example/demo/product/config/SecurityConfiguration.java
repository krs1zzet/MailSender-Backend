package com.example.demo.product.config;

import com.example.demo.product.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        /// Disable CSRF
        /// in general, it is not required for stateless REST services
        http
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/api/mails/").hasAnyRole("SIGNED")
                        .requestMatchers("/api/receivers").permitAll()
                        .requestMatchers("/api/receivers/**").permitAll()
                        .requestMatchers("/api/mails").permitAll()
                        .requestMatchers("/api/events").permitAll()
                        .requestMatchers("/api/events/**").permitAll()
                        .requestMatchers("/api/mailTemplates").permitAll()
                        .requestMatchers("/api/mailTemplates/**").permitAll()
                        .requestMatchers("/api/senders").permitAll()
                        .requestMatchers("/api/senders/**").permitAll()
                        .requestMatchers("/api/send-mail").permitAll()
                        .requestMatchers("/api/send-mail/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers(
                                "/api/auth/sign-up",
                                "/api/auth/sign-in",
                                "/app_status/init",
                                "/"
                        )
                        .permitAll());


        return http.build();
    }
}
