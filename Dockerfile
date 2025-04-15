# Usa la imagen base con JDK 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven al contenedor
COPY target/backendcramirez-0.0.1-SNAPSHOT.jar app.jar

# Muestra el contenido del directorio para confirmar que se copió correctamente
RUN ls -l .

# Comando por defecto al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
