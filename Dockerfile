FROM openjdk:21-jdk-slim
LABEL authors="Alexey Bobrovich"

COPY /target/*.jar timetracker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "timetracker-0.0.1.jar"]
