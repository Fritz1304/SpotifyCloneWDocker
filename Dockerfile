# Etapa 1: construir el proyecto
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto (genera el jar)
RUN mvn clean package -DskipTests

# Etapa 2: imagen final ligera para ejecutar
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el jar desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (ajusta si tu app usa otro)
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]