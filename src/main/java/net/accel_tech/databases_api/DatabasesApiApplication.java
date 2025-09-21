package net.accel_tech.databases_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
@EnableAsync
@EnableAutoConfiguration
public class DatabasesApiApplication {

	public static void main(String[] args) {

        SpringApplication.run(DatabasesApiApplication.class, args);

        System.out.println("Server started...");
	}

}
