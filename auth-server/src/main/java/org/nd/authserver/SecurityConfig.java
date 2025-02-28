package org.nd.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/oauth2/token").permitAll() // Allow token requests
                .anyRequest().authenticated()
        )
        .csrf(AbstractHttpConfigurer::disable)
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .formLogin(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                                              .clientId("client-id")
                                              .clientSecret("{noop}client-secret")
                                              .clientAuthenticationMethod(
                                                  ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                              .authorizationGrantType(
                                                  AuthorizationGrantType.AUTHORIZATION_CODE)
                                              .authorizationGrantType(
                                                  AuthorizationGrantType.REFRESH_TOKEN)
                                              .redirectUri(
                                                  "http://localhost:3000/login/oauth2/code/client")
                                              .scope(OidcScopes.OPENID)
                                              .scope("read")
                                              .scope("write")
                                              .build();

    return new InMemoryRegisteredClientRepository(client);
  }

  @Bean
  public OAuth2AuthorizationServerConfigurer<HttpSecurity> authServerConfigurer() {
    return new OAuth2AuthorizationServerConfigurer<>();
  }
}