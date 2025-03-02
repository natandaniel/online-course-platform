package org.nd.ocp.rest.api;

import org.nd.ocp.database.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DatabaseConfig.class)
public class RestApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestApiApplication.class, args);
  }
}