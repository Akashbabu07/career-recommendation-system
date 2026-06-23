# Career App — Backend

Spring Boot REST API for the career prediction platform. Handles user auth, profile management, and prediction history. Connects to MongoDB and communicates with the FastAPI ML service for job role predictions.

## What it does

- User registration and login with JWT access + refresh tokens
- Role-based access (USER / ADMIN)
- Store and retrieve prediction history per user
- User profile with CGPA, skills, interests, and projects
- Input validation and Spring Security on all protected routes
- Swagger UI available for API exploration

## Tech stack

- Java 21
- Spring Boot 3
- Spring Security + JJWT
- Spring Data MongoDB
- Lombok
- Bean Validation (Jakarta)
- SpringDoc OpenAPI (Swagger UI)

## Prerequisites

- Java 21
- Maven (or use the included `./mvnw` wrapper)
- MongoDB running locally on port `27017`

## Getting started

```bash
./mvnw clean package -DskipTests
java -jar target/*.jar
```

The API starts on `http://localhost:8080`.

If you have MongoDB on a different host or port, set the URI:

```bash
java -jar target/*.jar --spring.data.mongodb.uri=mongodb://yourhost:27017/careerdb
```

## API endpoints

### Auth — `/auth`

| Method | Path | Description |
|---|---|---|
| POST | `/auth/register` | Create a new account |
| POST | `/auth/login` | Log in, returns access + refresh token |
| POST | `/auth/refresh` | Get a new access token using refresh token |
| POST | `/auth/logout` | Invalidate refresh token |

### Users — `/users`

| Method | Path | Description |
|---|---|---|
| GET | `/users/profile` | Get current user's profile |

### Predictions — `/predictions`

| Method | Path | Description |
|---|---|---|
| POST | `/predictions/predict` | Submit skills, get a prediction back |
| GET | `/predictions/history` | Fetch prediction history for current user |

All `/users` and `/predictions` routes require a valid Bearer token in the `Authorization` header.

## Swagger UI

Once the app is running, open `http://localhost:8080/swagger-ui.html` to browse and test all endpoints interactively.

## Running with Docker

A Dockerfile is included. The image builds the JAR and runs it:

```bash
docker build -t career-backend .
docker run -p 8080:8080 \
  -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/careerdb \
  career-backend
```

Or just use Docker Compose from the root of the project — it wires everything up automatically including MongoDB.

## Data model

**User**
- `id`, `name`, `email` (unique), `password` (hashed), `cgpa`
- `skills`, `interests`, `projects` (string lists)
- `role` — `USER` or `ADMIN`

**Prediction**
- `id`, `email`, `skillsUsed`, `prediction`, `timestamp`
