# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# The application's JAR file
ARG JAR_FILE=build/libs/TennisMate-1.0-SNAPSHOT.jar

# Add the application's JAR file to the container
COPY ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
