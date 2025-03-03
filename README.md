<div align="center">
    <h1 align="center">ISIBook</h1>
    <p align="center">
        Laboratory room reservation system for systems engineering teachers
    </p>
</div>

</br>

## About The Project

This file documents the operation and construction of the backend of the project. This is a laboratory room reservation system intended for teachers, so they can **consult** and **make reservations** for the designated spaces of the _Escuela Colombiana de Ingeniería Julio Garavito_.

</br>

## Installation and configuration

Before download this repo, make sure you have installed:  
- **Java 17+**  
- **Maven 3.9.x**

Then you have to clone the repository:  
```sh
git clone https://github.com/usuario/proyecto.git
cd proyecto
```

Now, put the connection URI given by the cluster administrator and replace it in the API configuration. This is located in the resources directory and in the configuration file.

Finally, package and download the dependencies with the application with Maven and run the app:

```sh
mvn clean package
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

This is the model designed and used for reference of the database built in MongoDB. Also, it works as Physic Data Model

![Data model](/docs/Screenshots/DataModel.drawio.png)

</br>

## Test Driven Development
We will use this development standard with unit tests to be able to develop the application in the best possible way. For example, initially unit tests are performed and they will obviously fail. After implementing the code without having to modify the tests, the application must be able to be packaged and not give errors.
