# Используем официальный образ Maven для сборки проекта
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Используем официальный образ OpenJDK для запуска приложения
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/vacancy-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
