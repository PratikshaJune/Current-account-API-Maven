# Current Account API

This project is a Spring Boot microservices-based application for creating and managing current accounts. It includes the following services: `account-service`, `transaction-service`, `api-gateway`, `eureka-server`, and `frontend`.

## Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

## Running Locally

### Step 1: Clone the Repository

```sh
git clone <repository-link>
cd current-account-api
```

### Step 2: Build the Project

```sh
mvn clean install
```

### Step 3: Run Docker Compose

```sh
docker-compose up --build
```

### Step 4: Access the Services

- Eureka Server: http://localhost:8761
- API Gateway: http://localhost:8765
- Frontend: http://localhost:8080

### Running Test

```sh
mvn test
```

## CI/CD Pipeline

### Configure Docker Hub Secrets

- In your GitHub repository, go to Settings > Secrets.
- Add DOCKER_USERNAME and DOCKER_PASSWORD with your Docker Hub credentials.

### Triggering the Pipeline
The CI/CD pipeline is triggered by:

- Push to the main branch: Whenever code is pushed to the main branch.
- Pull request to the main branch: Whenever a pull request is created or updated targeting the main branch.