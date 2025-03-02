package org.nd.ocp.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.nd.ocp.database")
@EnableJpaRepositories(basePackages = "org.nd.ocp.database")
@ComponentScan(basePackages = "org.nd.ocp.database")
@EnableJpaAuditing
public class DatabaseConfig {
}
