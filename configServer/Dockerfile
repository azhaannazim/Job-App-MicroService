FROM maven:3.9-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-ubi9-minimal
WORKDIR /app
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/gateway-0.0.1-SNAPSHOT.jar"]