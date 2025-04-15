FROM eclipse-temurin:17-jdk

ARG JAR_FILE=target/*.jar

# Copia el JAR al contenedor
COPY ${JAR_FILE} app.jar

# Verifica si el archivo se copió correctamente
RUN ls -l /app.jar

# Comando para correr la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
