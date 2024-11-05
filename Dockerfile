FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/aeropuerto-a-0.0.1-SNAPSHOT.jar  /app/aeropuerto-a.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/aeropuerto-a.jar"]