package org.nd.ocp.authorization.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                                                        .clientId("ocp-auth-proxy")
                                                        .clientSecret(
                                                            passwordEncoder.encode("secret"))
                                                        .clientAuthenticationMethod(
                                                            ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                                        .authorizationGrantType(
                                                            AuthorizationGrantType.AUTHORIZATION_CODE)
                                                        .authorizationGrantType(
                                                            AuthorizationGrantType.REFRESH_TOKEN)
                                                        .redirectUri("http://127.0.0" +
                                                            ".1:8080/login/oauth2/code/ocp" +
                                                            "-auth-proxy")
                                                        .redirectUri(
                                                            "http://127.0.0.1:8080/authorized")
                                                        .scope("openid")
                                                        .scope("profile")
                                                        .scope("email")
                                                        .build();

    return new InMemoryRegisteredClientRepository(registeredClient);
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder()
                                      .issuer("http://localhost:9000")
                                      .build();
  }

}
