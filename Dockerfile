# Use official OpenJDK 17 slim image from docker hub
FROM openjdk:17-jdk-slim-bullseye

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Expose port 5000
EXPOSE 5000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
