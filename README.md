# System Metrics API

A Kotlin-based microservice built with the Ktor framework to expose basic system health and runtime metrics.

This project demonstrates a complete DevOps software delivery lifecycle using Gradle, Docker, GitHub Actions, Trivy, and planned AWS deployment with ECR and EC2.

---

## Project Overview

`system-metrics-api` is a lightweight REST API designed to showcase DevOps engineering practices around:

- Automated builds
- Unit/API testing
- Containerization
- CI pipeline automation
- Security scanning
- Cloud deployment readiness

The project is intended as a portfolio-ready DevOps project for internship applications.

---

## Tech Stack

| Area | Technology |
|---|---|
| Language | Kotlin |
| Framework | Ktor |
| Build Tool | Gradle Kotlin DSL |
| Testing | Kotlin Test, Ktor Test Host |
| Containerization | Docker |
| CI/CD | GitHub Actions |
| Security | Trivy |
| Cloud Target | AWS ECR, EC2 |

---

## API Endpoints

### Health Check

```http
GET /health
```

Response:

```text
UP
```

### System Metrics

```http
GET /metrics
```

Example response:

```json
{
  "freeMemory": 123456789,
  "totalMemory": 987654321,
  "maxMemory": 4294967296,
  "availableProcessors": 8
}
```

---

## Project Structure

```text
system-metrics-api/
├── .github/
│   └── workflows/
│       └── pipeline.yml
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── com/nouran/
│   │           └── Application.kt
│   └── test/
│       └── kotlin/
│           └── com/nouran/
│               └── ApplicationTest.kt
├── build.gradle.kts
├── settings.gradle.kts
├── Dockerfile
├── .dockerignore
├── gradlew
├── gradlew.bat
└── README.md
```

---

## Run Locally

### 1. Build and test the project

```bash
./gradlew clean test
```

On Windows PowerShell:

```powershell
.\gradlew clean test
```

### 2. Build the JAR

```bash
./gradlew clean jar
```

On Windows PowerShell:

```powershell
.\gradlew clean jar
```

---

## Run with Docker

### 1. Build Docker image

```bash
docker build -t system-metrics-api:local .
```

### 2. Run container

```bash
docker run -d --name system-metrics-api -p 8080:8080 system-metrics-api:local
```

### 3. Test the API

```bash
curl http://localhost:8080/health
```

Expected response:

```text
UP
```

For Windows PowerShell:

```powershell
curl.exe http://localhost:8080/health
```

### 4. Test metrics endpoint

```bash
curl http://localhost:8080/metrics
```

For Windows PowerShell:

```powershell
curl.exe http://localhost:8080/metrics
```

### 5. View container logs

```bash
docker logs system-metrics-api
```

### 6. Stop and remove container

```bash
docker stop system-metrics-api
docker rm system-metrics-api
```

---

## Docker Implementation

The project uses a multi-stage Docker build.

### Build Stage

The first stage uses a Gradle image to compile the Kotlin application and generate the executable JAR.

### Runtime Stage

The second stage uses a lightweight Java runtime image to run only the final application JAR.

This keeps the final container image cleaner by excluding source code, Gradle cache, and unnecessary build tools from the runtime image.

---

## Continuous Integration

GitHub Actions is configured to run a CI pipeline on push and pull requests.

Pipeline stages:

1. Checkout repository
2. Set up Java
3. Run Gradle tests
4. Build Docker image
5. Scan Docker image with Trivy

Workflow file:

```text
.github/workflows/pipeline.yml
```

---

## Security Scanning

The CI pipeline uses Trivy to scan the Docker image for vulnerabilities.

The scan checks:

- Operating system packages
- Application libraries
- High and critical vulnerabilities

This adds a security quality gate before deployment.

---

## Planned AWS Deployment

The next phase is deploying the containerized application to AWS.

Planned deployment flow:

```text
GitHub Actions
      ↓
Build and test
      ↓
Docker image build
      ↓
Trivy security scan
      ↓
Push image to Amazon ECR
      ↓
Deploy container on Amazon EC2
```

Planned AWS services:

- Amazon ECR for container image storage
- Amazon EC2 for running the container
- IAM for secure CI/CD permissions
- Security Groups for network access control

---

## DevOps Practices Demonstrated

This project demonstrates:

- Clean Gradle-based build automation
- Automated endpoint testing
- Reproducible Docker builds
- Multi-stage containerization
- CI pipeline automation
- Container vulnerability scanning
- Cloud deployment preparation
- SDLC documentation

---

## Current Status

Completed:

- Kotlin/Ktor REST API
- `/health` endpoint
- `/metrics` endpoint
- Gradle build configuration
- Automated test for `/health`
- Dockerfile
- `.dockerignore`
- Local Docker run
- GitHub Actions CI workflow
- Trivy image scanning

In progress:

- AWS ECR integration
- EC2 deployment automation

---

## Author

Developed by Nouran Atef