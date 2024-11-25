# Use an OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/golf-club-api-0.0.1-SNAPSHOT.jar golf-club-api.jar


# Expose port 8080 for the application
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "golf-club-api.jar"]
