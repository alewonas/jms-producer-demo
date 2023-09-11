# JMS Producer Demo

JMS Producer Demo is a Spring Boot application designed to function as an API for handling intake form information. It is specifically designed to handle HTTP requests containing intake form data. When an HTTP request is received, the application takes the body of the request and sends it as a message to an ActiveMQ (AMQ) message broker.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

To run this project locally, follow these steps:

1. Clone the repository

2. Navigate to the project directory

3. Build the project:
   mvn clean install
4. Run the Spring Boot application

5. The application should be accessible at `http://localhost:8080`. You can test it using the provided endpoints.

## Configuration

Before running the JMS Producer Demo, you need to configure your ActiveMQ connection details. Follow these steps to set up the necessary configuration:

1. Navigate to the `src/main/resources` directory in the project.

2. Open the `application.properties` file.

3. Locate the following properties and update them with your ActiveMQ server details if you are not using the defaults:

spring.activemq.broker-url=<Your ActiveMQ Broker URL><br>
spring.activemq.user=<Your ActiveMQ Username><br>
spring.activemq.password=<Your ActiveMQ Password>

## Usage

This application serves as an API for handling intake form information. It provides the following endpoints:

- `/intake/test` (GET): A test endpoint to check if the service is running.
- `/intake/submit` (POST): Submit an intake form JSON via HTTP to produce a message sent to the "intake-queue" in ActiveMQ.

Example usage:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
"firstName": "John",
"phone": "123-456-7890",
"email": "john@example.com",
"address": "123 Main St",
"age": 30,
"occupation": "Software Developer"
}' http://localhost:8080/intake/submit
