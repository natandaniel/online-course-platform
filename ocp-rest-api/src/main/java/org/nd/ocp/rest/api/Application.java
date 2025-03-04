package org.nd.ocp.rest.api;

import org.nd.ocp.database.DatabaseConfig;
import org.nd.ocp.domain.DomainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ DatabaseConfig.class, DomainConfig.class })
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}