package com.example.demo.product.filter;

import com.example.demo.features.auth.repository.TokenBlacklistRepository;
import com.example.demo.features.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  private final TokenBlacklistRepository tokenBlacklistRepository;

  // Herkese açık, token gerektirmeyen yolların listesi
  private static final List<String> PERMIT_ALL_PATHS = Arrays.asList(
          "/api/auth/sign-up",
          "/api/auth/sign-in",
          "/app_status/init",
          "/"
  );

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    // --- YENİ EKLENEN KONTROL BLOĞU ---
    final String path = request.getServletPath();

    // Eğer istek, herkese açık yollardan birine yapılıyorsa,
    // token kontrolü yapmadan bir sonraki filtreye geç.
    if (PERMIT_ALL_PATHS.contains(path)) {
      filterChain.doFilter(request, response);
      return; // Filtreyi burada sonlandır ve devam et
    }
    // --- KONTROL BLOĞU BİTİŞİ ---


    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      logger.warn("JWT WARNING: Authorization header is missing or malformed for protected path: {}", path);
      // Herkese açık olmayan bir yol için token yoksa isteği burada kesmek daha güvenli olabilir.
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or malformed.");
      return;
    }

    try {
      /// Extract the token from the header and remove the "Bearer " prefix
      final String token = authHeader.substring(7);
      // 🚨 Reject blacklisted tokens
      if (tokenBlacklistRepository.existsByToken(token)) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted.");
        return;
      }
      final String userEmail = jwtService.extractUsername(token);

      if (userEmail == null) {
        logger.error("JWT ERROR: Extracted username from token is null.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token: username is missing.");
        return;
      }

      /// Check if user is already authenticated
      if (SecurityContextHolder.getContext().getAuthentication() != null) {
        filterChain.doFilter(request, response);
        return;
      }

      /// Get user details from the database
      UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

      if (userDetails == null) {
        logger.error("JWT ERROR: UserDetailsService could not find user with email: {}", userEmail);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found.");
        return;
      }

      /// Check if the token is valid
      if (!jwtService.isTokenValid(token, userDetails)) {
        logger.error("JWT ERROR: Token is invalid for user: {}", userEmail);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token.");
        return;
      }

      /// Create and set authentication token
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());

      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);

      logger.info("JWT SUCCESS: User '{}' authenticated successfully.", userEmail);

      filterChain.doFilter(request, response);

    } catch (Exception e) {
      logger.error("SECURITY_ERROR: An error occurred while processing the JWT. {}", e.getMessage());
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied.");
    }
  }
}
