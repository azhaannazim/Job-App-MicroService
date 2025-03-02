# Job Application Microservices

A distributed system built with Spring Boot microservices for managing companies, job listings, and reviews.

## Project Overview

This system consists of three core microservices:
- **Company Service**: Manages company data (CRUD operations)
- **Job Service**: Handles job postings and management
- **Review Service**: Manages employee reviews and company ratings

Services communicate through REST APIs, message queues (RabbitMQ), and utilize service discovery with Eureka.

## Architecture Diagram
    Client -> API Gateway -> [Company Service | Job Service | Review Service]
           ↗               ↘
       Eureka Server     RabbitMQ
       Config Server     Zipkin
    
## Features

- **Company Management**
  - Create/Update/Delete companies
  - Get company details
- **Job Management**
  - Post new job listings
  - Search and manage job postings
- **Review System**
  - Submit employee reviews
  - Calculate company average ratings
  - Asynchronous review processing via message queue
- **System Features**
  - Service discovery and load balancing
  - API Gateway routing
  - Distributed tracing with Zipkin
  - Fault tolerance with Resilience4J
  - Centralized configuration

## Technologies Used

**Core Stack**
- Java 21
- Spring Boot 3.4.2
- Spring Cloud
- Maven

**Microservices Tools**
- Eureka Service Registry
- Spring Cloud Gateway
- OpenFeign Client
- Resilience4J
- Spring Cloud Config

**API & Communication**
- RESTful APIs
- RabbitMQ (Message Broker)
- RestTemplate

**Monitoring & Tracing**
- Zipkin Distributed Tracing
- Micrometer Metrics
- Sleuth

**Deployment**
- Docker
- Docker Compose
- Kubernetes (minikube)

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+
- Docker 20.10+
- Kubernetes cluster (optional)
- RabbitMQ
- Zipkin

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/job-app-ms.git


2. Build all services:
    ```bash
    mvn clean package -DskipTests

### Configuration

1. Service Registry (Eureka)
    ```bash
    cd service-registry
    mvn spring-boot:run

2. RabbitMQ (Docker)
     ```bash
    docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

3. Zipkin (Docker)
     ```bash
    docker run -d -p 9411:9411 openzipkin/zipkin

     
## Running Services

Start services in this order:

1. Service Registry
2. Config Server (if used)
3. Company Service
4. Job Service
5. Review Service
6. API Gateway

## API Documentation

### Company Service
| Endpoint            | Method | Description            |
|---------------------|--------|------------------------|
| `/companies`        | GET    | Get all companies      |
| `/companies/{id}`   | GET    | Get company by ID      |
| `/companies`        | POST   | Create new company     |
| `/companies/{id}`   | PUT    | Update company         |
| `/companies/{id}`   | DELETE | Delete company         |

**Example Request:**

    POST /companies
    Content-Type: application/json
    {
      "name": "Tech Corp",
      "description": "Technology solutions provider"
    }
### Job Service
| Endpoint       | Method | Description         |
|----------------|--------|---------------------|
| `/jobs`        | GET    | Get all jobs        |
| `/jobs/{id}`   | GET    | Get job by ID       |
| `/jobs`        | POST   | Create new job      |
| `/jobs/{id}`   | PUT    | Update job          |
| `/jobs/{id}`   | DELETE | Delete job          |

### Review Service
| Endpoint                              | Method | Description                     |
|---------------------------------------|--------|---------------------------------|
| `/reviews?companyId={id}`             | GET    | Get reviews by company          |
| `/reviews`                            | POST   | Submit new review               |
| `/reviews/averageRating?companyId={id}` | GET    | Get company average rating      |

**Example Review:**

    POST /reviews?companyId=1
    Content-Type: application/json    
    {
      "title": "Great workplace",
      "content": "Excellent growth opportunities",
      "rating": 4.5
    }
## Deployment

### Docker Compose
    docker-compose up --build
    
### Kubernetes (Minikube)

1. Start Minikube:
   ```bash
   minikube start

2. Create deployments:
   ```bash
    kubectl apply -f kubernetes/deployments/

3. Create services:
   ```bash
    kubectl apply -f kubernetes/services/

4. Verify deployment:
   ```bash
    kubectl get all
5. Access the application:
    ```bash
    minikube service <service-name> --url
    
## Additional Tools

- **Postman Collection**: Available in `/docs` directory
- **Zipkin Dashboard**: [http://localhost:9411](http://localhost:9411)
- **RabbitMQ Console**: [http://localhost:15672](http://localhost:15672) (username: `guest`, password: `guest`)


## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
