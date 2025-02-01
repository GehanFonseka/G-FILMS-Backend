package com.example.movies;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc  // Ensure CORS configurations apply properly
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("CORS configuration applied"); // Debugging log
                registry.addMapping("/**")  // Apply CORS to all API endpoints
                        .allowedOrigins("http://localhost:3000")  // Allow React app
                        .allowedMethods("*")  // Allow all HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true)  // Allow cookies or authentication
                        .maxAge(3600); // Cache preflight response for 1 hour
            }
        };
    }
}

