# Spring Boot Todo App

A simple Todo management REST API built with Spring Boot, utilizing an H2 in-memory database and documented with Swagger/OpenAPI.

## Features

- **CRUD Operations**: Complete API to Create, Read, Update, and Delete Todo items.
- **H2 In-Memory Database**: For quick and easy testing without external database dependencies.
- **UUID Primary Keys**: Secure and unique identifiers for each Todo created.
- **Swagger/OpenAPI Documentation**: Automatically generated API documentation with a sleek UI to test endpoints easily.
- **Lombok**: Reduced boilerplate code for models and entities.

## Requirements

- Java 21+

## How to Run

1. **Clone the repository**:

   ```bash
   git clone https://github.com/bhavna31sharma/swagger-hub-integration.git
   ```

2. **Run the application**:
   Use the included Maven wrapper to run the app directly from your terminal.

   On Windows:

   ```cmd
   .\mvnw spring-boot:run
   ```

   On macOS/Linux:

   ```bash
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

## API Documentation (Swagger UI)

Once the application is running, you can explore and interact with the REST API using the Swagger UI:

- **Swagger UI URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Endpoints

- `GET /api/todos`: Retrieve all Todo items.
- `GET /api/todos/{id}`: Retrieve a specific Todo item by its UUID.
- `POST /api/todos`: Create a new Todo item.
- `PUT /api/todos/{id}`: Update an existing Todo item by its UUID.
- `DELETE /api/todos/{id}`: Delete an existing Todo item by its UUID.
