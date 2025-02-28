package org.nd.ocp.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnlineCoursePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCoursePlatformApplication.class, args);
	}

}
