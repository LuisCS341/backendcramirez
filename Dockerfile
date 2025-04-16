# Etapa 1: Construcción del JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar solo el pom.xml primero para resolver dependencias y aprovechar cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del proyecto (código fuente, configs, etc.)
COPY src ./src

# Construir el proyecto, omitiendo tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR construido desde la etapa anterior
COPY --from=build /app/target/backendcramirez-0.0.1-SNAPSHOT.jar app.jar

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
