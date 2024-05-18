#FROM ubuntu:latest
FROM openjdk:17 as build
WORKDIR /app

COPY build/libs/user-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-0.0.1-SNAPSHOT.jar"]
