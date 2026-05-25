# Lending Engine

REST API for a peer-to-peer lending platform. Borrowers submit loan applications; lenders can browse requests and accept loans. Built with **Spring Boot** and **Spring Data JPA**.

> **Status:** Early development — core endpoints and domain models are in place; loan acceptance and related business logic are still being implemented.

## Tech stack

| | |
|---|---|
| **Runtime** | Java 25 |
| **Framework** | Spring Boot 4.0.6 |
| **API** | Spring Web MVC |
| **Persistence** | Spring Data JPA, H2 (in-memory) |
| **Observability** | Spring Boot Actuator |
| **Build** | Maven |

## Prerequisites

- [JDK 25](https://jdk.java.net/) (or a compatible JDK for the version in `pom.xml`)
- [Maven 3.9+](https://maven.apache.org/) (or use the included Maven Wrapper: `./mvnw` / `mvnw.cmd`)

## Getting started

### Clone and run

```bash
git clone <your-repo-url>
cd lendingengine
./mvnw spring-boot:run
```

On Windows:

```cmd
mvnw.cmd spring-boot:run
```

The API listens on **http://localhost:8080** by default.

### Build and test

```bash
./mvnw clean package
./mvnw test
```

## API overview

Base URL: `http://localhost:8080`

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/loan/request` | Submit a loan application |
| `GET` | `/loan/requests` | List all loan applications |
| `GET` | `/users` | List users |
| `POST` | `/loan/accept/{leaderId}/{loanApplicationId}` | Accept a loan (in progress) |

### Example: request a loan

```bash
curl -X POST http://localhost:8080/loan/request \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 5000,
    "borrowerId": 1,
    "daysToRepay": 30,
    "interestRate": 5.5
  }'
```

**Request body (`LoanRequest`):**

| Field | Type | Description |
|-------|------|-------------|
| `amount` | `int` | Loan amount |
| `borrowerId` | `long` | ID of the borrowing user |
| `daysToRepay` | `int` | Repayment term in days |
| `interestRate` | `double` | Interest rate |

On startup, the application seeds sample users (IDs `1`–`3`) for local development.

## Project structure

```
src/main/java/com/peerlender/lendingengine/
├── application/          # REST controllers and API DTOs
├── domain/
│   ├── model/              # JPA entities (User, LoanApplication, Loan)
│   ├── repository/         # Spring Data repositories
│   ├── service/            # Domain services and adapters
│   └── exception/
└── LendingengineApplication.java
```

## Configuration

Application settings live in `src/main/resources/application.properties`. The default profile uses an in-memory **H2** database suitable for local development.

## Roadmap

- [ ] Complete loan acceptance flow
- [ ] Validation and error responses for API clients
- [ ] Production database configuration
- [ ] API documentation (e.g. OpenAPI / Swagger)
- [ ] Authentication and authorization

## License

To be determined.
