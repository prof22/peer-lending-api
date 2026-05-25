# Lending Engine

REST API for a peer-to-peer lending platform. Borrowers submit loan applications; lenders accept applications and funded loans are persisted with repayment dates. Built with **Spring Boot** and **Spring Data JPA**.

> **Status:** Core lending flow is implemented (request → accept → list). Further work includes API error handling, validation, auth, and production-ready persistence. 

## Features

- Submit loan applications linked to registered borrowers
- List all pending loan applications
- Accept a loan application on behalf of a lender (creates a `Loan` record)
- List all funded loans with borrower, lender, amount, rate, and due dates
- List users (seed data loaded on startup for local development)
- Domain exceptions when a user or loan application is not found

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
| `POST` | `/loan/accept/{leaderId}/{loanApplicationId}` | Lender accepts a loan application |
| `GET` | `/loans` | List all funded loans |
| `GET` | `/users` | List all users |

On startup, the application seeds three sample users (IDs `1`–`3`) for local development.

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

Returns `404`-style behavior via `UserNotFoundException` if the borrower does not exist.

### Example: accept a loan

Use IDs from `GET /loan/requests` and a lender user ID from `GET /users`:

```bash
curl -X POST http://localhost:8080/loan/accept/2/1
```

- `leaderId` — lender user ID (path segment name in code)
- `loanApplicationId` — loan application ID

Creates a `Loan` with borrower and lender, amount, interest rate, `dateLent` (today), and `dateDue` based on the application term. Throws if the lender or application is not found.

### Example: list funded loans

```bash
curl http://localhost:8080/loans
```

Each loan includes borrower, lender, amount, interest rate, and lent/due dates.

## Project structure

```
src/main/java/com/peerlender/lendingengine/
├── application/          # REST controllers and API DTOs
├── domain/
│   ├── model/              # JPA entities (User, LoanApplication, Loan)
│   ├── repository/         # Spring Data repositories
│   ├── service/            # LoanService, LoanApplicationAdapter
│   └── exception/          # UserNotFoundException, LoanApplicationNotFoundException
└── LendingengineApplication.java
```

## Configuration

Application settings live in `src/main/resources/application.properties`. The default profile uses an in-memory **H2** database suitable for local development.

## Roadmap

- [x] Loan request and listing
- [x] Loan acceptance and funded loan listing
- [x] Basic domain exceptions (user / application not found)
- [ ] Structured API error responses (e.g. `@ControllerAdvice`)
- [ ] Request validation (Bean Validation)
- [ ] Remove or mark accepted applications from the open queue
- [ ] Production database configuration
- [ ] API documentation (e.g. OpenAPI / Swagger)
- [ ] Authentication and authorization

## License

To be determined.
