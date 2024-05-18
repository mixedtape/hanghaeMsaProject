#FROM ubuntu:latest
FROM openjdk:17 as build
WORKDIR /app

COPY build/libs/product-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "product-0.0.1-SNAPSHOT.jar"]
