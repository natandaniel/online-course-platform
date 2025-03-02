package org.nd.ocp.authorization.server;

import org.nd.ocp.database.DatabaseConfig;
import org.nd.ocp.domain.DomainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ DatabaseConfig.class, DomainConfig.class })
public class OAuth2AuthorizationServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
  }
}