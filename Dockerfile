FROM openjdk:21-jdk


# Copy the JAR file into the container
COPY target/joe-v3.jar app-v2.jar

# Expose port 80
EXPOSE 8080

# Define the entry point to run your application
ENTRYPOINT ["java", "-jar", "app-v2.jar"]