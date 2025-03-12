<div align="center">
    <h1 align="center">ISIBook</h1>
    <p align="center">
        Laboratory room reservation system for systems engineering teachers
    </p>
</div>

</br>

## About The Project

This file documents the operation and construction of the backend of the project. This is a laboratory room reservation system intended for teachers, so they can **create**, **consult**, **update** and **eliminate** for the designated spaces of the _Escuela Colombiana de Ingeniería Julio Garavito_.

</br>

## Installation and configuration

Before download this repo, make sure you have installed:  
- **Java 17+**  
- **Maven 3.9.x**

Then you have to clone the repository:  
```sh
git clone https://github.com/daviespr1406/Reservas_Sys.git
cd Reservas_Sys
```

Now, we are using Mongo Atlas as DB sistem, to change it, put the connection URI given by the cluster administrator and replace it in the API configuration. This is located in the resources directory and in the configuration file.

Finally, package and download the dependencies with the application with Maven and run the app:

```sh
mvn clean package verify
```

</br>

## Project Structure

### General

The project follows a modular structure to ensure maintainability and scalability. Below is a description of the main directories and files from an **API REST**:

- **controller/**: Defines the controllers that handle HTTP requests and manage communication between the frontend and backend.
- **service/**: Contains the business logic of the application. This layer processes data before interacting with the persistence layer.
- **repository/**: Handles database interactions, providing methods for performing CRUD operations on entities.
- **model/**: Defines the entities and data structures used in the application.
- **config/**: Contains custom configurations such as security settings, database connections, and CORS handling.
- **utils/**: Contains validations or identifications to facilitate the application of design patterns or tools that help us improve the quality of the app.
- **src/main/resources/**: Configuration files and static resources.
- **src/test/java/**: Contains unit and integration tests.

This structure ensures a clear separation of concerns, making the development and maintenance of the REST API more efficient.

</br>

### Architecture

The architecture represented in the image is a client-server architecture with a RESTful API; this maintains the construction of model, view, and controller, but with the backend and frontend decoupled.

![Archotecture](/docs/Screenshots/Arquitectura.drawio.png)

</br>

### Data Model

The structure of the data that goes into persistence has an already defined structure that, in theory, is adjusted from the front end to ensure that everything works correctly.
We have several databases in our cluster. For now, we'll only use one main database called "ISIBook," which will work until the specific schedules are implemented. In this database, we have the following documents:
- **Reserves**: Data related to reservations made by a user.


```json
{
    s
}
```


</br>


- **Classrooms**: Data related to the characteristics and functionalities of a specific room.

```json
{
    "_id": {
        "$oid":"<AUTO>"
    },
    "name":<STRING>,
    "build":<STRING>,
    "specs": {
        "capacity:<STRING>,
        "type":<STRING>,
        "numComputers":<STRING>,
        "pcTypes":<STRING>,
        "projector":<BOOLEAN>,
        "touchScreen":<BOOLEAN>,
        "airConditioning":<STRING>
    }
}
```

EXAMPLE:

```json
{
    "_id": {
        "$oid":"67d15664e4df91310a73ccdc"
    },
    "name":"H301",
    "build":"H",
    "specs": {
        "capacity":"45",
        "type":"Laboratory",
        "numComputers":"40",
        "pcTypes":"Laptop",
        "projector":true,
        "touchScreen":false,
        "airConditioning":"Complete"
    }
}
```


</br>


- **Users**: Created by a super administrator and divided into two types, admin and teacher.

#### ADMIN

```json
{
    "_id":{
        "$oid":<AUTO>
    },
    "name":<STRING>,
    "password":<STRING>,
    ,"username":<STRING>,
    ,"register": {
        "$date":{"<DATE>"}
    },
    "type":"admin"
}
```

EXAMPLE:

```json
{
    "_id":{
        "$oid":"67d160d8b3b48052db4b2adc"
    },
    "name":"Aurora",
    "password":"aA0."
    ,"username":"auroraAdmin"
    ,"register": {
        "$date":{"$numberLong":"1741737600000"}
    },
    "type":"admin"
}
```

</br>

#### TEACHER

```json
{
  "name": <STRING>,
  "password": <STRING>,
  "username": <STRING>,
  "register": {
    "$date": "<DATE>"
  },
  "mail": "<USERNAME>@escuelaing.edu.co",
  "status": <STRING>,
  "type": "teacher"
}
```

EXAMPLE:

```json
{
    "_id": {
        "$oid":"67d16201b3b48052db4b2add"
    },
    "name":"Andres Felipe Chavarro",
    "password":"Ac9.",
    "username":"andres.chavarro-p",
    "register": {
        "$date":{"$numberLong":"1741737600000"}
    },
    "mail":"andres.chavarro-p@escuelaing.edu.co",
    "status":"active",
    "type":"teacher"
}
```
</br>

#### Model

This is the model designed and used for reference of the database built in MongoDB. Also, it works as Physic Data Model

![Data model](/docs/Screenshots/DataModel.drawio.png)

</br>
</br>

## Endpoints
To make the application easier to understand, in this section we will summarize all the possible endpoints for use in any frontend.

### Reserves
...

</br>

### Users


#### `GET /users`
- **Description**: Retrieve all registered users.
- **Query Parameters**:
  - `name` _(optional)_: Filter by user name.  
  - `role` _(optional)_: Filter by user role (`ADMIN`, `USER`, etc.).  
- **Successful Response**:
  ```json
  [
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "role": "USER"
    },
    {
      "id": 2,
      "name": "Jane Smith",
      "email": "jane@example.com",
      "role": "ADMIN"
    }
  ]
    ```
- **Error Response**:
        400 Bad Request: Invalid parameters.
        500 Internal Server Error: Server error.




  
</br>

### Classrooms


</br>
</br>

## Test Driven Development
We will use this development standard with unit tests to be able to develop the application in the best possible way. For example, initially unit tests are performed and they will obviously fail. After implementing the code without having to modify the tests, the application must be able to be packaged and not give errors.

![Jacoco test](/docs/Screenshots/jacoco.png)

</br>

## Sonar
We use a static code analysis tool like Sonar, which allows us to validate functionality, troubleshoot various issues, confirm the correct use of design patterns, and create clean code.
This analysis is automatically generated by a pipeline every time a PR is submitted to development.

![Sonar image](/docs/Screenshots/sonar.png)
