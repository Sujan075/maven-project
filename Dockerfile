
# Stage 1: Build the Maven application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Copy source code
COPY src ./src

# Build application and run JUnit tests
RUN mvn clean package

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the generated Maven JAR
COPY --from=build /app/target/maven-project-0.0.1-SNAPSHOT.jar app.jar

# Execute Mobile Recharge Application
CMD ["java", "-cp", "app.jar", "com.example.maven_project.App"]
