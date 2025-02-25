package org.nd.online_course_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authz ->
            authz.requestMatchers("/api/v1/auth/**")
                 .permitAll()
                 .requestMatchers(HttpMethod.GET, "/api/v1/courses/**")
                 .hasAnyRole("STUDENT", "ADMIN")
                 .requestMatchers(HttpMethod.POST, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 .requestMatchers(HttpMethod.PUT, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE, "/api/v1/courses/**")
                 .hasRole("ADMIN")
                 .anyRequest()
                 .authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    return http.build();
  }
}