# Course Management System

A backend system for managing courses, students, instructors, enrollments, and related course management operations.

The project is implemented using **Spring Boot** and **PostgreSQL**, with the backend divided into two independent services:

- **Admin Service**: Handles administrative operations and management features.
- **Public Service**: Handles public-facing operations and user access.

The complete system is containerized using **Docker** and orchestrated using **Docker Compose**.

---

# System Architecture

The project consists of three main containers:

```
                     Docker Network

                          |
        -------------------------------------
        |                  |                |
        v                  v                v

 PostgreSQL          Admin Service     Public Service
 Database            Spring Boot      Spring Boot
 Port: 5432          Port: 8081       Port: 8082
```

The Spring Boot applications communicate with PostgreSQL through the Docker network using the service name:

```
postgres:5432
```

instead of:

```
localhost:5432
```

---

# Technologies Used

## Backend

- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- Spring Web MVC
- Spring Validation
- Maven

## Database

- PostgreSQL 18

## Containerization

- Docker
- Docker Compose v5.3.0

---

# Project Structure

```
coursemanagement/

│
├── docker-compose.yml
│
├── admin/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│
├── public/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│
└── README.md
```

---

# Services

## Admin Service

Responsible for administrative operations.

Container name:

```
admin-service
```

Running on:

```
http://localhost:8081
```

Spring Boot configuration:

```properties
server.port=8081
```

---

## Public Service

Responsible for public operations.

Container name:

```
public-service
```

Running on:

```
http://localhost:8082
```

Spring Boot configuration:

```properties
server.port=8082
```

---

## PostgreSQL Database

Database container:

```
course-postgres
```

Configuration:

```
Database Name:
course_management_db

Username:
cms_user

Password:
cms_password

Port:
5432
```

---

# Prerequisites

Before running the project, make sure you have installed:

## Java

Required:

```
Java 21
```

Check:

```bash
java -version
```

---

## Maven

Check:

```bash
mvn -version
```

---

## Docker

Check:

```bash
docker --version
```

---

## Docker Compose

Check:

```bash
docker compose version
```

---

# Running the Project Locally (Without Docker)

Each Spring Boot service can be run independently.

---

## Run Admin Service

Navigate to:

```bash
cd admin
```

Run:

```bash
mvn spring-boot:run
```

---

## Run Public Service

Navigate to:

```bash
cd public
```

Run:

```bash
mvn spring-boot:run
```

---

# Running Using Docker Compose (Recommended)

The complete system can be started using one command.

From the project root directory:

```
coursemanagement/
```

Run:

```bash
docker compose up --build
```

This will:

1. Build the Admin Docker image.
2. Build the Public Docker image.
3. Pull PostgreSQL 18 image.
4. Create the PostgreSQL database container.
5. Start all services.

---

# Stopping the Application

To stop all containers:

```bash
docker compose down
```

The PostgreSQL data is preserved because the database uses a Docker volume:

```
postgres_data
```

---

# Docker Commands

## View Running Containers

```bash
docker ps
```

---

## View All Containers

```bash
docker ps -a
```

---

## View Application Logs

All services:

```bash
docker compose logs -f
```

Admin service:

```bash
docker logs admin-service
```

Public service:

```bash
docker logs public-service
```

---

## Rebuild Images

After changing code or dependencies:

```bash
docker compose up --build
```

---

# Database Persistence

The PostgreSQL database uses a Docker volume:

```yaml
volumes:
  postgres_data:
```

This means:

- Containers can be removed safely.
- Database data remains available.
- Restarting the project does not recreate the database from scratch.

---

# Database Connection Configuration

Both Spring Boot applications use:

```properties
spring.datasource.url=jdbc:postgresql://postgres:5432/course_management_db
spring.datasource.username=cms_user
spring.datasource.password=cms_password
```

The hostname:

```
postgres
```

is the Docker Compose service name.

---

# Docker Compose Services

The main `docker-compose.yml` contains:

```yaml
services:

  postgres:
    image: postgres:18

  admin:
    build:
      context: ./admin

  public:
    build:
      context: ./public
```

All services communicate through the default Docker Compose network.

---

# Current Status

✅ Admin Service Dockerized  
✅ Public Service Dockerized  
✅ PostgreSQL 18 Containerized  
✅ Docker Compose Configuration Completed  
✅ Database Persistence Configured  
✅ Spring Boot Services Connected Successfully  

---

# Future Improvements

Possible future enhancements:

- Add frontend application
- Add authentication and authorization
- Add API documentation using Swagger/OpenAPI
- Add environment variables using `.env`
- Add CI/CD pipeline
- Deploy containers to cloud platforms

---

# Author

Course Management System Team