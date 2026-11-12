
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/maven-project-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-cp", "app.jar", "com.example.maven_project.App"]
