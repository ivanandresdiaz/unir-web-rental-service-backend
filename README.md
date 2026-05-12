# Rental Vehicle — Microservices System

A microservices-based vehicle rental management system built with **Java 17**, **Spring Boot 3.5**, **Spring Cloud 2025**, **PostgreSQL 16** and **Docker**.
This project is part of the _Construcción de Aplicaciones Web_ assignment at UNIR (Universidad Internacional de La Rioja).

---

## Architecture

```
                  ┌─────────────┐
                  │   Client    │
                  └──────┬──────┘
                         │
                         ▼
                  ┌─────────────┐        ┌──────────────┐
                  │   Gateway   │ ─────▶ │    Eureka    │
                  │   :8080     │        │    :8761     │
                  └──────┬──────┘        └──────────────┘
                         │                     ▲ ▲
              ┌──────────┴──────────┐          │ │ register
              ▼                     ▼          │ │
       ┌────────────┐        ┌──────────────┐  │ │
       │  Vehicle   │◀──────▶│  Operation   │──┘ │
       │   :8091    │ Feign  │    :8092     │────┘
       └─────┬──────┘        └──────┬───────┘
             │                      │
             ▼                      ▼
       ┌──────────┐           ┌──────────────┐
       │vehicle-db│           │ operation-db │
       │  :5433   │           │    :5434     │
       └──────────┘           └──────────────┘
```

| Service          | Port | Description                                              |
| ---------------- | ---- | -------------------------------------------------------- |
| `rental-vehicle` | 8761 | Eureka discovery server                                  |
| `gateway`        | 8080 | Spring Cloud Gateway (single entry point)                |
| `vehicle`        | 8091 | Vehicle catalog microservice                             |
| `operation`      | 8092 | Rental operations microservice (calls vehicle via Feign) |
| `vehicle-db`     | 5433 | PostgreSQL 16 database for `vehicle`                     |
| `operation-db`   | 5434 | PostgreSQL 16 database for `operation`                   |

---

## Tech stack

- Java 17 (Eclipse Temurin)
- Spring Boot 3.5.14
- Spring Cloud 2025.0.2 (Eureka, Gateway WebFlux, OpenFeign)
- Spring Data JPA
- PostgreSQL 16
- Springdoc OpenAPI / Swagger UI 2.3.0
- Gradle 8.14 (with wrapper)
- Project Lombok
- Docker / Docker Compose

---

## Project structure

```
rental-vehicle/
├── personal.postman_collection.json   # Postman collection (v2.1) — import in Postman
├── rental-vehicle/        # Eureka server
├── gateway/               # Spring Cloud Gateway
├── vehicle/               # Vehicle microservice
├── operation/             # Rental operations microservice
├── infraestructure/
│   ├── docker-compose.yml
│   └── docker/            # init.sql for each database
└── render.yaml            # Optional Render.com deployment manifest
```

### Postman (importar la colección)

1. Abre **Postman** (escritorio o web).
2. Pulsa **Import** (arriba a la izquierda).
3. Arrastra `personal.postman_collection.json` desde la raíz del repositorio, o elige **Upload Files** / **Select files** y selecciona ese archivo.
4. Confirma **Import** cuando Postman detecte el formato **Collection v2.1**.

La colección usa por defecto `http://localhost:8080` (mismo puerto que el **gateway**). Si las rutas de las peticiones no coinciden con esta API, edítalas según la sección [API summary](#api-summary) (por ejemplo `/api/v1/vehicle` y `/api/v1/operation`).

---

## Prerequisites

To run with Docker (recommended):

- Docker Desktop **or** Docker Engine 24+
- Docker Compose v2

To run locally without Docker:

- JDK 17
- Gradle (the included wrapper `./gradlew` works out of the box)
- PostgreSQL 16 running locally

---

## Quick start with Docker Compose (recommended)

This single command builds all four JARs, builds the images and starts the entire stack:

```bash
# 1. Build every Spring Boot JAR (run once, and after every code change)
./gradlew clean bootJar -p rental-vehicle
./gradlew clean bootJar -p gateway
./gradlew clean bootJar -p vehicle
./gradlew clean bootJar -p operation

# 2. Start the stack (Eureka, Gateway, both microservices and both databases)
cd infraestructure
docker compose up --build -d
```

> **Windows note:** replace `./gradlew` with `gradlew.bat`.

Once everything is up:

| URL                                    | Description                       |
| -------------------------------------- | --------------------------------- |
| http://localhost:8761                  | Eureka dashboard                  |
| http://localhost:8080/api/v1/vehicle   | Vehicle API through the gateway   |
| http://localhost:8080/api/v1/operation | Operation API through the gateway |
| http://localhost:8091/swagger-ui.html  | Vehicle Swagger UI (direct)       |
| http://localhost:8092/swagger-ui.html  | Operation Swagger UI (direct)     |

Stop and clean everything:

```bash
docker compose down -v
```

---

## API summary

### Vehicle (`/api/v1/vehicle`)

| Method | Path                             | Description                                |
| ------ | -------------------------------- | ------------------------------------------ |
| POST   | `/`                              | Create a new vehicle                       |
| GET    | `/{vehicleId}`                   | Get vehicle by id                          |
| PUT    | `/{vehicleId}`                   | Update vehicle                             |
| DELETE | `/{vehicleId}`                   | Soft-delete vehicle                        |
| GET    | `/`                              | Search by `brand`, `model` or `statusCode` |
| GET    | `/{vehicleId}/availability`      | Returns `true` / `false`                   |
| PUT    | `/{vehicleId}/status/{statusId}` | Change vehicle status                      |

### Operation (`/api/v1/operation`)

| Method | Path                      | Description                                  |
| ------ | ------------------------- | -------------------------------------------- |
| POST   | `/rent`                   | Create a new rental (validates availability) |
| GET    | `/rent/{rentalId}`        | Get rental by id                             |
| PUT    | `/rent/{rentalId}/cancel` | Cancel rental and free up the vehicle        |

### Example: create a rental

```bash
curl -X POST http://localhost:8080/api/v1/operation/rent \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "vehicleId":  1,
    "startDate":  "2026-05-15T09:00:00",
    "endDate":    "2026-05-18T09:00:00"
  }'
```

---
