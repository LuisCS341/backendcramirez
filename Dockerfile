# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Define el JAR que se copiará dentro del contenedor
ARG JAR_FILE=target/*.jar


# Copia el JAR al contenedor
COPY ${JAR_FILE} app.jar

# Comando para correr la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
