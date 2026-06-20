
# The Autonomous AI Workflow System - ProcessMind AI

[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.15-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next.js-15-000000?style=for-the-badge&logo=next.js&logoColor=white)](https://nextjs.org/)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=white)](https://react.dev/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Flyway](https://img.shields.io/badge/Flyway-11.7.2-CC0000?style=for-the-badge&logo=flyway&logoColor=white)](https://flywaydb.org/)
[![Docker](https://img.shields.io/badge/Docker-24-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

ProcessMind AI is an AI-powered Process Intelligence Platform that converts natural-language Standard Operating Procedures (SOPs) into executable, validated, and optimizable business workflows.

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Database Migrations](#database-migrations)
- [API](#api)
- [Security](#security)
- [Contributing](#contributing)
- [License & Contacts](#license--contacts)

---

## Overview

ProcessMind AI ingests SOPs (PDF, DOCX, TXT), extracts business rules and decision logic, generates workflow models, validates correctness, simulates outcomes, and orchestrates execution with human approvals and monitoring.

## Key Features

- SOP ingestion and NLP extraction
- Automatic workflow & decision model generation
- Validation (missing approvals, circular dependency detection)
- Scenario simulation and bottleneck detection
- Runtime orchestration and approvals
- Analytics and AI-driven optimization suggestions

## Architecture

- Presentation: Next.js (frontend)
- Application: Spring Boot backend with REST APIs
- Process Intelligence: SOP parser, workflow generator, simulator
- Orchestration: Workflow engine, approval and notification services
- Data: PostgreSQL + Flyway for migrations

## Tech Stack

- Frontend: Next.js, React, TypeScript, Tailwind CSS
- Backend: Java 21, Spring Boot 3.5.x, Spring Security, Spring Data JPA
- Database: PostgreSQL 17, Flyway
- Infrastructure: Docker, Docker Compose

---

## Quick Start

Prerequisites: Java 21, Maven 3.9+, Node.js 18+, PostgreSQL 17, Docker (optional)

Clone and run locally:

```bash
git clone https://github.com/yourusername/processmind-ai.git
cd processmind-ai

# Backend
cd ProcessMind-AI/backend
cp .env.example .env
# Edit .env with your DB credentials
./mvnw clean install
./mvnw spring-boot:run

# Frontend (in another terminal)
cd ../../frontend
npm install
npm run dev
```

Run with Docker Compose:

```bash
docker-compose up -d --build
```

Access:

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui.html

---

## Project Structure

- [ProcessMind-AI/backend](ProcessMind-AI/backend) — Spring Boot application (services, controllers, entities, Flyway migrations)
- [frontend](frontend) — Next.js frontend
- `docker-compose.yml` — orchestration for local development

Browse the backend code starting at [ProcessMind-AI/src/main/java/com/processmind](ProcessMind-AI/backend/src/main/java/com/processmind)

---

## Database Migrations

Flyway migrations are under `ProcessMind-AI/backend/src/main/resources/db/migration`. Example migration files included (V1..V10).

---

## API

Base URL: `http://localhost:8080/api/v1`

Authentication: JWT Bearer tokens for protected endpoints.

Example request:

```http
GET /api/v1/workflows?status=DEPLOYED&page=0&size=20
Authorization: Bearer <your_jwt_token>
```

---

## Security

- JWT with refresh tokens
- Role-based access control (RBAC)
- BCrypt password hashing
- Input validation and parameterized queries to prevent SQL injection
- HTTPS recommended for production

---

## Contributing

Contributions are welcome. Please fork, create a feature branch, add tests, and open a Pull Request. Keep commit messages clear and follow the project's coding standards.

Suggested workflow:

```bash
git checkout -b feature/my-feature
# make changes
git commit -m "feat: add ..."
git push origin feature/my-feature
```

---

## License & Contacts

This project is proprietary. Copyright © 2026 ProcessMind AI. All rights reserved.

For questions or support: mohamedsakir3456@gmail.com

---

If you want a version with more visuals (architecture diagrams, screenshots, or badges adjusted), tell me which assets to include and I will add them.
