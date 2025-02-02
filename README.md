# G-Films Backend

The backend of the G-Films application is built using **Java Spring Boot**. This API serves the necessary data for the G-Films frontend, including movie trailers, reviews, and wishlists. The backend provides endpoints for user authentication, managing movie data, and handling reviews.

## Features

- **User Authentication**: Secure login and registration using JWT (JSON Web Tokens).
- **Movie Management**: Retrieve movie data and trailers through API endpoints.
- **Review System**: Allows users to post and view reviews for movies.
- **Wishlist Management**: Users can add movies to their wishlist for future viewing.
- **RESTful API**: All interactions are handled via HTTP requests and responses.

## Technologies Used

- **Java Spring Boot**: For building the backend RESTful API.
- **Spring Security**: For handling user authentication and authorization.
- **JPA (Java Persistence API)**: For managing data persistence and database interactions.
- **MongoDB**: For storing movie, review, and wishlist data in a NoSQL database.
- **JWT**: For secure authentication and token-based authorization.
- **Maven**: For dependency management and building the project.
- **Swagger**: For API documentation.

## Setup & Installation

Follow the steps below to set up the backend locally.

### 1. Clone the repository

```bash
git clone https://github.com/GehanFonseka/G-Films-Backend.git
```

### 2. Navigate into the project folder

```bash
cd G-Films-Backend
```
### 3. Install dependencies:

```bash
mvn clean install
```
### 4. Run the application

```bash
mvn spring-boot:run
```


