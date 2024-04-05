# Panta Store

## Description

Basic online store management system developed using Spring Boot and Java.
It provides a solution for managing both products and categories within a store.

The application allows for the creation, retrieval, update, and deletion of products and categories.
It uses Maven as a build tool, and its functionality is thoroughly tested using JUnit and Mockito.

## Technologies Used

- Java 21: The main programming language used for developing the application.
- Maven: A build automation tool used primarily for Java projects.
- Spring Boot: An open-source Java-based framework used to create stand-alone, production-grade Spring based Applications.
- JUnit: A simple framework to write repeatable tests in Java, used for testing the application.
- Mockito: A mocking framework for unit tests in Java.
- Docker: A platform used to develop, ship, and run applications inside containers.
- Docker Compose: A tool for defining and running multi-container Docker applications.
- Makefile: A build automation tool that automatically builds executable programs and libraries from source code.
- Swagger: An open-source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful web services.

## API Documentation

This project uses Swagger to document the API. Swagger provides a set of tools to design, build, and document RESTful APIs. You can view the API documentation by running the application and navigating to `/swagger-ui.html` in your web browser (replace `/` with the base URL of your application if it's not running locally).

The Swagger UI provides a visual interface for exploring the API. It lists all the available endpoints, their HTTP methods, expected parameters and responses, and even allows you to try out the endpoints directly in the browser.

## API Endpoints

### Product Endpoints
- GET `/products`: Fetch all products
- POST `/products`: Add a new product
- GET `/products/{id}`: Fetch a specific product by its ID

### Category Endpoints
- GET `/categories`: Fetch all categories
- POST `/categories`: Create a new category
- GET `/categories/{id}`: Fetch a category by its ID
- PUT `/categories/{id}`: Update an existing category
- DELETE `/categories/{id}`: Delete a category

## Makefile Usage

The project includes a `Makefile` to simplify the build and test process. The `Makefile` provides a set of commands that you can use to build, test, and manage the project without having to remember complex command line instructions.

Here are the available commands:

- `make version`: This command will display the Java version being used in the Docker container.

- `make clean`: This command will clean the project using Maven, removing all generated files.

- `make test`: This command will run the project's tests using Maven.

- `make install`: This command will install the project's dependencies and package the project using Maven.

To use these commands, open a terminal in the project's root directory and type `make` followed by the command. For example, to run the tests, you would type `make test`.

The `Makefile` is particularly useful in this project as it ensures that all developers are using the same commands in a standardized environment, reducing the potential for inconsistencies between different developers' local setups.

## Three Musketeers Pattern

This project applies the [Three Musketeers](https://3musketeers.io/) pattern, which is a pattern for developing software in a repeatable and consistent manner. It leverages Docker, Compose, and Make to ensure that the environment remains the same across any platform.

The `Makefile` in this project is a key component of this pattern. It wraps Docker and Docker Compose commands, allowing you to build, test, and manage the project regardless of your local environment. This ensures that all developers are using the same commands in a standardized environment, reducing the potential for inconsistencies between different developers' local setups.

Here's how the Three Musketeers pattern is applied:

- Docker: Each command is run inside a Docker container, ensuring that the command has the same behavior regardless of where it's run.

- Compose: Docker Compose is used to manage the application's services. In this project, it's used to run the Java application in a Docker container.

- Make: The `Makefile` provides a simple interface to the project's commands. Instead of having to remember and type complex Docker or Docker Compose commands, you can run simple make commands like `make test` or `make install`.

By using the Three Musketeers pattern, this project ensures that every developer has the same experience when building, testing, and managing the project, regardless of their local environment.