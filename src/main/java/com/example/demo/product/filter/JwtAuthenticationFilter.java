package com.example.demo.product.filter;

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


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      if (authHeader.startsWith("Bearer ")) {
        /// Extract the token from the header and remove the "Bearer " prefix
        final String token = authHeader.substring(7);
        /// Extract the user email from the token
        final String userEmail = jwtService.extractUsername(token);

        /// If the user email is not null and the user is not already authenticated
        /// then authenticate the user
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

          /// Get user details from the database
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          /// Check if user details are valid and if the token is valid
          if (jwtService.isTokenValid(token, userDetails)) {
            /// Create an authentication token and set the user details
            /// by passing the user details and the authorities
            /// which are received from database
            UsernamePasswordAuthenticationToken authToken = new
                UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
            );

            /// Enforce the authentication token to have the details of the request
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            /// Update the security context with the authentication token
            SecurityContextHolder.getContext().setAuthentication(authToken);
          }
        }
      }
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      logger.error("SECURITY_ERROR: An error occurred while processing the JWT. {}",
      e.getMessage());
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied");
    }
  }
}
