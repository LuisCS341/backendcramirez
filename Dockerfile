FROM eclipse-temurin:17-jdk

# Copia el JAR específico al contenedor
COPY target/backendcramirez-0.0.1-SNAPSHOT.jar app.jar

# (Opcional) Verifica si el archivo se copió correctamente
RUN ls -l /app.jar

# Comando para correr la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
