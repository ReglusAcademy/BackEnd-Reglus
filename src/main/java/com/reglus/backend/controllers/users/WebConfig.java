package com.reglus.backend.controllers.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Adiciona o mapeamento para as rotas que começam com /api/
                .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:8081") // Adicione ambas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowCredentials(true);
    }
}
