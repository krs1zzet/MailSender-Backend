package com.example.demo.product.filter;


import com.example.demo.features.auth.service.JwtService;
import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final String cookieName;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String method = request.getMethod();
        String path = request.getRequestURI();

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (log.isDebugEnabled()) {
                log.debug("[JWT] context already authenticated -> skip ({} {})", method, path);
            }
            chain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request, cookieName);
        if (!StringUtils.hasText(token)) {
            if (log.isDebugEnabled()) {
                log.debug("[JWT] no token -> anonymous ({} {})", method, path);
            }
            chain.doFilter(request, response);
            return;
        }
        if (log.isDebugEnabled()) log.debug("[JWT] token found : {}", token);

        if (!jwtService.isValid(token)) {
            log.warn("[JWT] token INVALID (signature/expiration)");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            return;
        }
        if (log.isDebugEnabled()) log.debug("[JWT] token is valid");

        Optional<String> sub = jwtService.getSubject(token);
        if (sub.isEmpty()) {
            log.warn("[JWT] subject missing in token");
            chain.doFilter(request, response);
            return;
        }
        String username = sub.get();
        if (log.isDebugEnabled()) log.debug("[JWT] subject(username)={}", username);

        User user = userService.findByUsernameReturnUser(username);
        if (user == null) {
            log.warn("[JWT] user not found in DB username={}", username);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String roleName = user.getRole() != null ? user.getRole().getName() : "USER";
        if (log.isDebugEnabled()) log.debug("[JWT] DB user id={} role={}", user.getId(), roleName);

        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleName));

        var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        if (log.isDebugEnabled())
            log.debug("[JWT] authentication set -> principal={} authorities={}", username, authorities);


        chain.doFilter(request, response);
    }


    private String resolveToken(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (Objects.equals(cookieName, c.getName())) {
                    return c.getValue();
                }
            }
        }
        String authz = request.getHeader("Authorization");
        if (StringUtils.hasText(authz) && authz.startsWith("Bearer ")) {
            return authz.substring(7);
        }
        return null;
    }

}
