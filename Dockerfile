# 1. Bau-Phase: Wir nutzen ein Image mit Maven und Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Wir bauen die App (ohne Tests, um Zeit zu sparen)
RUN mvn clean package -DskipTests

# 2. Start-Phase: Wir nehmen ein schlankes Java Image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Wir kopieren die fertige JAR-Datei aus der Bau-Phase
COPY --from=build /app/target/*.jar app.jar

# Der Port, den Render uns gibt
ENV PORT=8080
EXPOSE 8080

# Der Start-Befehl
ENTRYPOINT ["java","-jar","app.jar"]