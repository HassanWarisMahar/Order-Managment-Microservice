# Order Management System

This project implements an **Order Management System** using a **microservices architecture**. It incorporates several best practices commonly used in the world of microservices development, offering a scalable, maintainable, and flexible solution. The system is built with **Spring Boot** for the backend and **Docker** for containerization.

## Key Features

- **Microservices Architecture**: The system is divided into modular services that can independently scale, maintain, and extend as needed.

- **Spring Cloud Netflix Eureka**: Provides service registration and discovery, allowing services to dynamically register and find each other.

- **Spring Cloud Open Feign**: Enables easy communication between microservices via synchronous REST API calls.

- **Spring Cloud Gateway**: Centralized API Gateway to manage API access, simplifying communication with different microservices.

- **Resilience4j Circuit Breaker**: Prevents cascading failures by enabling fallback mechanisms if a service is unavailable, enhancing system reliability.

- **Docker Compose**: Simplifies the process of running the entire application and its dependencies through a single configuration file.

## Microservices Overview

### Product Service

The **Product Service** handles product creation and retrieval. It maintains a dedicated database for product data. When a new product is created, the service communicates with the **Inventory Service** to adjust the stock quantities accordingly.

### Order Service

The **Order Service** allows clients to place orders by providing a list of product IDs. It verifies product availability through the **Inventory Service** and ensures the requested quantities are in stock. Once the availability is confirmed, the order is placed and the inventory is updated. If the **Inventory Service** is down, a fallback response is returned using the **Circuit Breaker** pattern.

### Inventory Service

The **Inventory Service** manages the stock quantities of products. It provides endpoints for checking, adding, and updating product inventory.


## Database

The project uses **H2 Database** for managing data. H2 is an open-source, lightweight, in-memory relational database that is well-suited for development, testing, and prototyping. It provides an embedded SQL database, making it easy to set up and use without requiring complex configurations.

### H2 Database Setup

- **In-memory Database**: By default, the H2 database is configured to run in-memory, which means the data will not persist between application restarts. This setup is ideal for development or testing environments.

- **Persistence**: If you want to persist data, H2 can be configured to store data in a file-based database. You can change this configuration by modifying the `application.properties` or `application.yml` file.

### Database Configuration

The **H2 Database** is embedded into the Spring Boot application and configured through the `application.properties` file. By default, the application uses the H2 database in memory mode.

Example `application.properties` for H2 Database configuration:

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true


## Accessing the Application

- **Eureka Dashboard**: View the service registration and discovery at [http://localhost:8761/](http://localhost:8761/).

## Available Endpoints


| Method | Path                              | Description                                    |
|--------|-----------------------------------|------------------------------------------------|
| GET    | http://localhost:8080/products    | Retrieve all products                          |
| POST   | http://localhost:8080/products    | Create a new product                           |
| GET    | http://localhost:8080/orders      | Retrieve all orders                            |
| POST   | http://localhost:8080/orders      | Create a new order                             |
| GET    | http://localhost:8080/inventory   | Get stock availability for products (by productId) |
| PUT    | http://localhost:8080/inventory   | Update inventory quantities                    |
| POST   | http://localhost:8080/inventory   | Add products to inventory                      |


### Endpoint Details

- **GET /products**: Retrieves all available products in the system.

- **POST /products**: Allows for the creation of a new product in the system.

- **GET /orders**: Fetches a list of all orders placed in the system.

- **POST /orders**: Allows clients to place a new order by submitting a list of product IDs and quantities.

- **GET /api/v1/inventory**: Checks the stock availability for a list of product IDs. The request requires a query parameter `productId` (array of strings).

- **PUT /api/v1/inventory**: Updates the inventory quantities for products. The request body should contain a list of `InventoryRequest` objects with product IDs and new quantities.

- **POST /api/v1/inventory**: Adds products to the inventory. The request body should include a list of `InventoryRequest` objects with product IDs and quantities to add.
```

### Example Request Bodies

#### Create Product (POST /products)

```json
{
    "productName": "Samsung Galaxy S24 Ultra",
    "productDescription": "Embrace the Galaxy A.I.",
    "price": 168000,
    "quantity": 100
}



### Example body for Create Order endpoint

```json
{
    {
    "billingAddress":"House No. 123, F-5/1",
    "deliveryAddress":"House No. 123, F-5/1",
    "orderItemRequests":[
        {
            "productId":"product id",
            "quantity":1
        }
    ]
}
}
```
### Postman cURL

#### Inventory Check

```curl
curl --location 'http://localhost:8080/inventory?productId=22031f77-18cf-4822-9585-e72574221984' \
--header 'accept: application/json' \
--data ''
```
#### Add Product

```curl
curl --location 'http://localhost:8080/products' \
--header 'Content-Type: application/json' \
--data '{
        "productName": "Motorola Edge 40 Pro",
        "productDescription": "Flagship performance with a sleek design and immersive display.",
        "price": 100000,
        "quantity": 1000
    }'
```

#### Create Order

```curl
curl --location 'http://localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data '{
    "billingAddress": "Systems Limited F-5/1",
    "deliveryAddress": "Systems Limited F-5/1",
    "orderItemRequests": [
        {
            "productId": "24236585-a3d8-4084-94b1-b73492568fc6",
            "quantity": 140
        },
        {
            "productId": "04ceaf94-b017-4dd7-9f03-81f459b0f8b3",
            "quantity": 35
        },
        {
            "productId": "da709b0e-8de4-48fa-9fbb-2dd9b087f96d",
            "quantity": 30
        }
    ]
}
'
```
