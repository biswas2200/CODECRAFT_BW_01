# CODECRAFT_BW_01
 THIS REPOSITORY CONTAINS CODECRAFT  BACK END DEVELOPMENT TASK 01
# User Management REST API

## Overview

This REST API provides CRUD operations to manage users. It supports the following actions:
- **Create** a new user
- **Retrieve** all users or a single user by UUID
- **Update** an existing user's mutable fields (name, email, and age)
- **Delete** a user by UUID

The API enforces the following validations:
- **Email Validation:** The email field must contain an "@" symbol.
- **Age Validation:** The age must not be greater than 90.

Custom exception handling is implemented so that any invalid input returns a meaningful error with the appropriate HTTP status code (e.g., 400 Bad Request for invalid input, 404 Not Found for non-existent resources).

> **Note:**  
> A new UUID is generated for every created user and is visible when fetching user details. The UUID is immutable and cannot be updated—only deleted.

## API Endpoints

### Create a New User

- **URL:** `POST /api/users`
- **Description:** Creates a new user. A unique UUID is automatically generated.
- **Validations:**
    - The email must contain an `@` symbol.
    - The age must not be greater than 90.
- **Request Body Example:**

    ```json
    {
      "name": "Alice Smith",
      "email": "alice.smith@example.com",
      "age": 28
    }
    ```

- **Success Response:**
    - **HTTP Status:** `201 Created`
    - **Response Body Example:**

      ```json
      {
        "id": "123e4567-e89b-12d3-a456-426614174000",
        "name": "Alice Smith",
        "email": "alice.smith@example.com",
        "age": 28
      }
      ```

- **Error Response:**
    - **HTTP Status:** `400 Bad Request`
        - If the email is missing/invalid or if age is greater than 90.

---

### Retrieve All Users

- **URL:** `GET /api/users`
- **Description:** Retrieves a list of all users.
- **Success Response:**
    - **HTTP Status:** `200 OK`
    - **Response Body Example:**

      ```json
      [
        {
          "id": "123e4567-e89b-12d3-a456-426614174000",
          "name": "Alice Smith",
          "email": "alice.smith@example.com",
          "age": 28
        },
        {
          "id": "223e4567-e89b-12d3-a456-426614174001",
          "name": "John Doe",
          "email": "john.doe@example.com",
          "age": 35
        }
      ]
      ```

---

### Retrieve a User by ID

- **URL:** `GET /api/users/{id}`
- **Description:** Retrieves a single user by its UUID.
- **Path Parameter:**
    - `id` (UUID) - The unique identifier of the user.
- **Success Response:**
    - **HTTP Status:** `200 OK`
    - **Response Body Example:**

      ```json
      {
        "id": "123e4567-e89b-12d3-a456-426614174000",
        "name": "Alice Smith",
        "email": "alice.smith@example.com",
        "age": 28
      }
      ```

- **Error Response:**
    - **HTTP Status:** `404 Not Found`
        - If no user exists with the provided UUID.

---

### Update a User

- **URL:** `PUT /api/users/{id}`
- **Description:** Updates an existing user's information (name, email, and age). The UUID remains immutable.
- **Path Parameter:**
    - `id` (UUID) - The identifier of the user to update.
- **Validations:**
    - Email must contain an `@` symbol.
    - Age must not be greater than 90.
- **Request Body Example:**

    ```json
    {
      "name": "Alice Johnson",
      "email": "alice.johnson@example.com",
      "age": 29
    }
    ```

- **Success Response:**
    - **HTTP Status:** `200 OK`
    - **Response Body Example:**

      ```json
      {
        "id": "123e4567-e89b-12d3-a456-426614174000",
        "name": "Alice Johnson",
        "email": "alice.johnson@example.com",
        "age": 29
      }
      ```

- **Error Response:**
    - **HTTP Status:** `400 Bad Request`
        - If the email is invalid or the age is greater than 90.
    - **HTTP Status:** `404 Not Found`
        - If the user with the provided UUID does not exist.

---

### Delete a User

- **URL:** `DELETE /api/users/{id}`
- **Description:** Deletes an existing user by its UUID.
- **Path Parameter:**
    - `id` (UUID) - The unique identifier of the user to delete.
- **Success Response:**
    - **HTTP Status:** `204 No Content`
- **Error Response:**
    - **HTTP Status:** `404 Not Found`
        - If the user with the provided UUID does not exist.

---

## Exception Handling

The API uses custom exception handling with a global exception handler to provide meaningful error responses.

### Custom Exceptions

- **UserNotFoundException:** Thrown when a user is not found.
- **BadRequestException:** Thrown when the input is invalid (e.g., malformed email address or age greater than 90).

### Global Exception Handler

Using `@ControllerAdvice`, all exceptions are handled centrally. For example:
- `UserNotFoundException` returns a `404 Not Found` with a descriptive error message.
- `BadRequestException` returns a `400 Bad Request` with the appropriate error message.
- Any other exceptions return a `500 Internal Server Error`.

---

## Summary

This User Management REST API implements robust functionality with built-in input validations and centralized error handling. The endpoints include:

- **POST** `/api/users` to create a user with automatic UUID generation.
- **GET** `/api/users` to list all users.
- **GET** `/api/users/{id}` to retrieve a specific user by UUID.
- **PUT** `/api/users/{id}` to update user details (immutable UUID).
- **DELETE** `/api/users/{id}` to delete a user.

Follow these guidelines and example payloads for smooth integration and usage of the API.
