# Spring Boot CRUD Application with DTO, Validation, and Exception Handling

A RESTful API built with Java and Spring Boot to manage student records.
The application uses separate request and response DTOs, input
validation, custom exceptions, and centralized exception handling.

## Technologies Used

-   Java 21
-   Spring Boot 4.1.2
-   Spring Web MVC
-   Spring Data JPA
-   Hibernate (through JPA, if used by the configured persistence setup)
-   Jakarta Bean Validation
-   MySQL Connector/J
-   Maven

## Features

-   Create a student record
-   Retrieve all student records
-   Retrieve a student by ID
-   Update a student record
-   Delete a student record
-   Soft-delete a student record
-   Use separate DTOs for create and update requests and responses
-   Validate incoming create and update requests using `@Valid`
-   Handle errors through custom exceptions and a global exception
    handler

## Project Structure

``` text
src/main/java/com/example/DtoCrudApplication/
├── controller/
│   └── StudentController.java
├── dto/
│   ├── CreateStudentRequestDto.java
│   ├── CreateStudentResponseDto.java
│   ├── UpdateStudentRequestDto.java
│   ├── UpdateStudentResponseDto.java
│   ├── ExceptionResponseDto.java
│   └── ValidationExceptionResponseDto.java
├── entity/
│   └── Student.java
├── exception/
│   ├── DuplicateResourceException.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/
│   └── StudentRepository.java
├── service/
│   └── StudentService.java
└── DtoCrudApplication.java

src/main/resources/
└── application.properties
```

## Layered Architecture

-   **Controller:** Defines REST endpoints and handles HTTP requests.
-   **DTO:** Separates API request/response data from the entity model.
-   **Service:** Contains the student-related business logic.
-   **Repository:** Provides the data-access layer.
-   **Entity:** Represents student data for persistence.
-   **Exception:** Contains custom exceptions and centralized exception
    handling.

## API Endpoints

The base path for the student API is `/students/api`.

  Method     Endpoint               Description
  ---------- ---------------------- -----------------------------
  `POST`     `/students/api`        Create a student
  `GET`      `/students/api`        Retrieve all students
  `GET`      `/students/api/{id}`   Retrieve a student by ID
  `PUT`      `/students/api/{id}`   Update a student by ID
  `DELETE`   `/students/api/{id}`   Delete a student by ID
  `PATCH`    `/students/api/{id}`   Soft-delete a student by ID

### Validation

The create and update controller methods use `@Valid` on their request
DTOs. The validation rules themselves are defined in the DTO classes.

### Exception Handling

The project includes:

-   `DuplicateResourceException` for duplicate-resource scenarios
-   `ResourceNotFoundException` for resources that cannot be found
-   `GlobalExceptionHandler` for centralized exception handling
-   `ExceptionResponseDto` and `ValidationExceptionResponseDto` for
    error-response data

The exact error response fields depend on the DTO definitions and
exception-handler implementation.

## Database Configuration

Configure the database connection in
`src/main/resources/application.properties`.

Example for a local MySQL database (replace the placeholders with your
own settings):

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Do not commit real database passwords or other secrets to GitHub. Add
any JPA settings required by your project to the same configuration
file.

## Getting Started

### Prerequisites

-   JDK 21
-   Maven (or use the included Maven Wrapper)
-   MySQL, if using the configured MySQL database

### Run Locally

1.  Clone your repository:

    ``` bash
    git clone YOUR_GITHUB_REPOSITORY_URL
    ```

2.  Open the project in IntelliJ IDEA or another Java IDE.

3.  Configure the database in
    `src/main/resources/application.properties`.

4.  From the project root, build the application:

    ``` bash
    ./mvnw clean install
    ```

    On Windows Command Prompt, use:

    ``` bat
    mvnw.cmd clean install
    ```

    If Maven is installed globally, you can use `mvn clean install`.

5.  Run the application:

    ``` bash
    ./mvnw spring-boot:run
    ```

    On Windows Command Prompt, use:

    ``` bat
    mvnw.cmd spring-boot:run
    ```

6.  Test the endpoints using Postman or another REST client.

## Testing the API

Use Postman to send requests to the endpoints listed above. The JSON
request fields and example response bodies should be taken from the
project's DTO classes so that the examples match the implementation.

## Author

SANKET UGALMUGALE

-   GitHub: https://github.com/sanket-ugalmugale
