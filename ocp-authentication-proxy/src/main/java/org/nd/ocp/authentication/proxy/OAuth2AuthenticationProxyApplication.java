package org.nd.ocp.authentication.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuth2AuthenticationProxyApplication {
  public static void main(String[] args) {
    SpringApplication.run(OAuth2AuthenticationProxyApplication.class, args);
  }
}