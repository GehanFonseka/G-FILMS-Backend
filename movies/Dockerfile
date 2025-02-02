# Use OpenJDK 17 as the base image
FROM openjdk:17-slim AS build

# Install Maven (ensure the version fits your needs)
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container to '/app'
WORKDIR /app

# Copy the entire repository from the parent directory to the container's /app directory
COPY ../ .

# Change to the 'movies' directory (where your pom.xml is located)
WORKDIR /app/movies

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for running the app
FROM openjdk:17-slim

# Set environment variables for MongoDB connection
ENV MONGO_DATABASE=movie-api-db
ENV MONGO_USER=gehanfonseka
ENV MONGO_PASSWORD=movieapi
ENV MONGO_CLUSTER=movieapi.rejtt.mongodb.net
ENV SPRING_PROFILES_ACTIVE=prod

# Copy the JAR file from the build image
COPY --from=build /app/movies/target/movies-0.0.1-SNAPSHOT.jar movies.jar

# Expose the application port
EXPOSE 9191

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "movies.jar"]
