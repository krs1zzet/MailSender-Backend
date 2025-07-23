package com.example.demo.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            // Bütün izin verilen adresleri tek bir .allowedOrigins() metodu içinde listelemelisiniz.
            .allowedOrigins(
                    "http://localhost:3000",
                    "http://localhost:8080",
                    "https://fastidious-queijadas-9bb76b.netlify.app",
                    "https://d42pbemxfsmlh.cloudfront.net" // YENİ EKLENEN ADRES
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
  }
}