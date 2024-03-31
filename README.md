# Panta Store

## Description

Basic online store management system developed using Spring Boot and Java. 
It provides a solution for managing both products and categories within a store.

The application allows for the creation, retrieval, update, and deletion of products and categories. 
It uses Maven as a build tool, and its functionality is thoroughly tested using JUnit and Mockito.

## Technologies Used

- Java
- Maven
- Spring Boot
- JUnit
- Mockito

## API Endpoints

### Product Endpoints
- GET `/products`: Fetch all products
- POST `/products`: Add a new product
- GET `/products/{id}`: Fetch a specific product by its ID

### Category Endpoints
- GET `/categories`: Fetch all categories
- GET `/categories/{id}`: Fetch a category by its ID
- POST `/categories`: Create a new category
- PUT `/categories/{id}`: Update an existing category
- DELETE `/categories/{id}`: Delete a category