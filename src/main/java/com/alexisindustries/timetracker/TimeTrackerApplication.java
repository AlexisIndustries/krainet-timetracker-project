package com.alexisindustries.timetracker;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Time Tracker REST API",
                description = "Rest API Documentation of Time Trackers Application",
                version = "v0.0.1",
                contact = @Contact(
                        name = "Alexey Bobrovich",
                        email = "abobrovitch@gmail.com",
                        url = "https://github.com/AlexisIndustries"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Rest API Documentation"
        )
)
@SpringBootApplication
public class TimeTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackerApplication.class, args);
    }
}
