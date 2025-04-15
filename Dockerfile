# Etapa 1: Construcción del JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/backendcramirez-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
