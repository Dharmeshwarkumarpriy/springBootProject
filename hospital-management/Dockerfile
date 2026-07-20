 FROM maven:3.8.4-openjdk-17 AS builder
 FROM eclipse-temurin:17-jdk-jammy
 VOLUME [ "/tmp" ]
 WORKDIR /app
 COPY target/hospital-management-0.0.1-SNAPSHOT.jar app.jar
 EXPOSE 8080
 ENTRYPOINT [ "java","-jar","/app.jar" ]
