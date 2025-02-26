package org.nd.online_course_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private OAuth2UserService oAuth2UserService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/v1/users/register", "/error")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/courses/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/courses/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/courses/**")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/courses/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated())
        .oauth2Login(
            oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService)));
    return http.build();
  }
}