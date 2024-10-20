FROM maven:3.9.4-eclipse-temurin-21 AS build
LABEL authors="Alexey Bobrovich"

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
LABEL authors="Alexey Bobrovich"

WORKDIR /app

COPY --from=build /app/target/*.jar timetracker-0.0.1.jar

ENTRYPOINT ["java", "-jar", "timetracker-0.0.1.jar"]

