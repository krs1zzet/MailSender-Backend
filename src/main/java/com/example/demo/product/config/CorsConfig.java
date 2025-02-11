package com.example.demo.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("localhost:8080")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}