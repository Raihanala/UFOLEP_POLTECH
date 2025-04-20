package com.ufolep.polytech.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Toutes les routes
                        .allowedOrigins("http://localhost:3000") // Attention aux espaces
                        .allowedMethods("*") // GET, POST, PUT, DELETE...
                        .allowedHeaders("*") // Tous les headers autorisés
                        .allowCredentials(true); // Pour les cookies, tokens, etc.
            }
        };
    }
}
