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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final AntPathMatcher PM = new AntPathMatcher();
    // Bu yollar filtreden tamamen muaf: signin/signup vs. CORS preflight ve actuator
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/auth/**",
            "/actuator/**",
            "/health"           // Caddy’deki basit health’i de rahat bırak
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        final String method = request.getMethod();
        final String path = request.getRequestURI();

        // Preflight istekleri filtreden geçmesin
        if ("OPTIONS".equalsIgnoreCase(method)) return true;

        // Bypass list
        for (String p : EXCLUDED_PATHS) {
            if (PM.match(p, path)) return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {


        // Zaten authenticated ise bırak
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(request, response);
            return;
        }

        // Cookie veya Authorization header’dan token çöz
        String token = resolveToken(request, cookieName);
        if (!StringUtils.hasText(token)) {
            if (log.isDebugEnabled()) log.debug("[JWT] No token -> continue as anonymous");
            chain.doFilter(request, response);
            return;
        }

        // Token doğrula — GEÇERSİZSE BİLE zinciri kesme (signin/signup kırılmasın)
        if (!jwtService.isValid(token)) {
            if (log.isWarnEnabled()) log.warn("[JWT] Invalid token (signature/exp). Continuing without auth.");
            chain.doFilter(request, response);
            return;
        }

        Optional<String> sub = jwtService.getSubject(token);
        if (sub.isEmpty()) {
            if (log.isWarnEnabled()) log.warn("[JWT] Missing subject in token. Continuing without auth.");
            chain.doFilter(request, response);
            return;
        }

        String username = sub.get();
        User user = userService.findByUsernameReturnUser(username);
        if (user == null) {
            if (log.isWarnEnabled()) log.warn("[JWT] User not found in DB username={}", username);
            chain.doFilter(request, response);
            return;
        }

        String roleName = (user.getRole() != null ? user.getRole().getName() : "USER");
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
