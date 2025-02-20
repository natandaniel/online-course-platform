# 📚 Online Learning Platform

**A scalable Java-based learning platform to deliver high-quality Java programming courses with seamless course enrollment, progress tracking, and payment integration.**

![Project Status](https://img.shields.io/badge/Status-In_Development-yellow.svg)
![Java](https://img.shields.io/badge/Java-23-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green.svg)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)

---

## 🚀 Features
- User registration and authentication (JWT)
- Course catalog with **Modules** and **Lessons**
- Secure **one-time course payments** via Stripe
- Progress tracking for enrolled students
- Admin dashboard for **course, user, and payment management**
- REST API with **Swagger UI documentation**
- Fully containerized with **Docker & Kubernetes**

---

## 🛠️ Tech Stack

| Layer | Technology                                   |
|---------|----------------------------------------------|
| **Backend** | Java 23, Spring Boot 3, Spring Security, JPA |
| **Database** | PostgreSQL                                   |
| **Payments** | Stripe                                       |
| **Auth** | JWT (JSON Web Tokens)                        |
| **Messaging (Optional)** | Kafka                                        |
| **Frontend** | (To be determined: React, Angular, Vue)      |
| **Containerization** | Docker, Kubernetes                           |
| **CI/CD** | GitHub Actions, Jenkins                      |

---

## 📂 Additional Documentation

For more details on specific aspects of the system, refer to the documentation files in the `docs/` directory:

| Topic                                       | File                                                                                             |
|---------------------------------------------|--------------------------------------------------------------------------------------------------|
| **Technical Architecture & Infrastructure** | [`docs/technical-architecture-infrastructure.md`](docs/technical-architecture-infrastructure.md) |
| **Functional Requirements**                 | [`docs/functional-requirements.md`](docs/functional-requirements.md)                             |
| **Non functional Requirements**             | [`docs/non-functional-requirements.md`](docs/non-functional-requirements.md)                     |
| **API Reference**                           | [`docs/rest-api-overview.md`](docs/rest-api-overview.md)                                         |
| **Testing Strategy**                        | [`docs/testing-quality-assurance.md`](docs/testing-quality-assurance.md)                         |
| **Deployment Guide**                        | [`docs/deployment-devops-strategy.md`](docs/deployment-devops-strategy.md)                       |
| **Risk Assessment & Mitigation Strategy**   | [`docs/risk-assessment-mitigation-strategy.md`](docs/risk-assessment-mitigation-strategy.md)     |