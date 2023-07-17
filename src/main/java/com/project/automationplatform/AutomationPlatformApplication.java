package com.project.automationplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		FlywayAutoConfiguration.class,
		SecurityAutoConfiguration.class
})
public class AutomationPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationPlatformApplication.class, args);
	}

}
