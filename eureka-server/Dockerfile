# Use the official OpenJDK 17 image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/eureka-server-1.0.0.jar app.jar

# Make port 8761 available to the world outside this container
EXPOSE 8761

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
