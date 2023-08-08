# **Spring Boot API Project for CRUD Operations with PostgreSQL**

This is an example of a Spring Boot API project that performs CRUD (Create, Read, Update, Delete) operations using a PostgreSQL database. The API allows you to manage user information, including their usernames and ages.

### API Endpoints
The API has the following endpoints to perform CRUD operations:

- **Create User**
    - Method: POST
    - URL: `/users`
    - Request Body (JSON):
      ```json
      {
        "userName": "exampleUser",
        "age": 25
      }
      ```

- **List Users**
    - Method: GET
    - URL: `/users`

- **Get User by ID**
    - Method: GET
    - URL: `/users/{id}`

- **Update User**
    - Method: PUT
    - URL: `/users/{id}`
    - Request Body (JSON):
      ```json
      {
        "userName": "updatedUser",
        "age": 30
      }
      ```

- **Delete User**
    - Method: DELETE
    - URL: `/users/{id}`

### Testing the API
I use Postman app for testing endpoints.

