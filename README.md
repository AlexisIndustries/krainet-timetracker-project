# Time Tracker System

This is a simple time tracking application built with Spring Boot and PostgresSQL as the database. It includes two user roles: admin, user. Each role has specific actions, such as recording work hours, managing users, and handling stores.

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- Liquibase
- PostgresSQL Driver
- Lombok
- JWT
- Spring Doc

## Requirements

To build and run the application, you'll need:

- Any JDK 21
- Maven

## Running the Application

1. Clone this repository:
```git clone https://github.com/Yurwar/time-tracker-spring.git```

2. Install Docker on your machine
3. Run: ```mvn package```
4. Run:
```docker-compose up -d```
5. Go to http://localhost:7890/swagger-ui/index.html#/
6. Go to security/token endpoint and get your JWT token
7. Use generated JWT to make requests