package org.nd.online_course_platform.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // Enables method-level security annotations like @PreAuthorize
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(
            AbstractHttpConfigurer::disable) // Disable CSRF for simplicity; configure it
        // properly for production
        .authorizeHttpRequests(authz ->
            authz.requestMatchers("/api/v1/auth/**")
                 .permitAll()// Public endpoints (like auth endpoints)
                 .requestMatchers(HttpMethod.GET, "/api/v1/courses/**")
                 .hasAnyRole("STUDENT", "ADMIN")
                 .requestMatchers(HttpMethod.POST, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 .requestMatchers(HttpMethod.PUT, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 // Other endpoints require authentication
                 .anyRequest()
                 .authenticated()
        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}