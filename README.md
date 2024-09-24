# 📚 U-Future test task

A simple library management system built with **Spring Boot** that allows users to manage a collection of books. This project provides basic CRUD operations for managing books and includes RESTful API documentation with **Swagger**.

## 🚀 Features

- 📖 Add, update, and delete books from the collection.
- 🔍 Retrieve details of a book by its ID.
- 📚 Fetch the entire list of books available.
- 🛠️ Global exception handling for user-friendly error responses.
- 📃 API documentation using **Swagger**.

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Swagger (OpenAPI 3)**
- **Maven**

## 📖 API Endpoints

The following endpoints are available for managing books:

| Method   | Endpoint               | Description                           |
|----------|------------------------|---------------------------------------|
| `GET`    | `/books`                | Fetch all books                       |
| `GET`    | `/books/{id}`           | Fetch a specific book by ID           |
| `POST`   | `/books`                | Add a new book                        |
| `PUT`    | `/books/{id}`           | Update an existing book by ID         |
| `DELETE` | `/books/{id}`           | Delete a book by ID                   |
