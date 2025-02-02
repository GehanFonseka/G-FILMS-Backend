# Use Maven base image to build the application
FROM maven:3.8.8-openjdk-17 AS build

# Set working directory in the container
WORKDIR /app

# Copy the entire project to the working directory
COPY . .

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for running the app
FROM openjdk:17.0.12-slim

# Set environment variables for MongoDB connection
ENV MONGO_DATABASE=movie-api-db
ENV MONGO_USER=gehanfonseka
ENV MONGO_PASSWORD=movieapi
ENV MONGO_CLUSTER=movieapi.rejtt.mongodb.net
ENV SPRING_PROFILES_ACTIVE=prod

# Copy the JAR file from the build image
COPY --from=build /app/target/movies-0.0.1-SNAPSHOT.jar movies.jar

# Expose the application port
EXPOSE 9191

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "movies.jar"]
