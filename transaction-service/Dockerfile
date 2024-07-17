# Use the official OpenJDK 17 image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/transaction-service-1.0.0.jar app.jar

# Make port 8082 available to the world outside this container
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
