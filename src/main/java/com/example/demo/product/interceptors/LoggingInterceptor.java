package com.example.demo.product.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull Object handler) {
    logger.info("Request : {} Method: {}", request.getRequestURL(), request.getMethod());
    return true;
  }

  @Override
  public void afterCompletion(@NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull Object handler, Exception ex) {
    logger.info("Response Status: {}", response.getStatus());
  }
}