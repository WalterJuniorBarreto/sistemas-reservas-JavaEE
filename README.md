# Booking Core Backend | Enterprise Reservation System

A monolithic transactional backend system designed with **Java EE (Jakarta EE 10)** and **PostgreSQL**. This project strictly applies Software Engineering principles, Clean Architecture, and Cybersecurity practices for the secure lifecycle management of reservations and users.

## Software Architecture & Design Patterns

This project rejects "Spaghetti Code" and applies the **MVC (Model-View-Controller)** standard with strictly separated layers:

* **Presentation Layer (Controllers):** Agile Servlets focused exclusively on HTTP routing and request/response management.
* **Business Layer (Services):** Isolates business rules, complex validations, and transactional logic.
* **Data Access Layer (DAO):** Implements the *Data Access Object* pattern to encapsulate all SQL statements.
* **Connection Pooling (Singleton):** Utilizes **HikariCP** in static memory to reuse connections, avoiding the latency of creating new database connections for every request.

## Implemented Cybersecurity

* **SQL Injection Prevention:** Strict use of `PreparedStatement` across all DAOs.
* **Secure Cryptography:** Password hashing using `BCrypt`. Credentials are never stored in plain text.
* **Role-Based Access Control (RBAC):** Implementation of the Interceptor Pattern (`AuthFilter`) to block *Forced Browsing* and prevent Privilege Escalation.
* **XSS and CSRF Prevention:** View sanitization using JSTL tags (`<c:out>`) and state mutation restricted exclusively to HTTP `POST` methods.
* **IDOR Protection:** Logical deletion (Soft Delete) cryptographically verifies that the reservation belongs to the currently authenticated user in the session.

## DevOps & Deployment (12-Factor App)

The project is designed to be "Environment-Agnostic", ready for CI/CD pipelines:

* **Environment Configuration:** Database credentials are dynamically injected via **Environment Variables** (`DB_URL`, `DB_USER`, `DB_PASSWORD`), using `database.properties` solely as a local development fallback.
* **Observability:** Integration of **SLF4J** for structured event logging instead of the standard `System.out`.
* **Containerization:** Includes a `Dockerfile` optimized on `tomcat:10.1-jre17-temurin`, applying attack surface reduction by removing Tomcat's default applications.

## Technologies & Clean Code

* **Language:** Java 21
* **Web Framework:** Jakarta EE 10 (Servlets, JSP, JSTL)
* **Database:** PostgreSQL 15+
* **Dependency Manager:** Maven
* **Connection Pool:** HikariCP
* **Clean Code:** Adherence to SOLID principles, DRY (Don't Repeat Yourself), and descriptive naming conventions without magic numbers.

## Local Installation Guide

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/WalterJuniorBarreto/sistemas-reservas-JavaEE.git](https://github.com/WalterJuniorBarreto/sistemas-reservas-JavaEE.git)
    ```
2.  **Database Configuration:**
    Create a database named `sistemas-reservas` in PostgreSQL and execute the SQL scripts for table creation (Users, Reservations, ENUMs).
3.  **Compile the Artifact (WAR):**
    ```bash
    mvn clean package
    ```

## Architectural Evolution (Another way to do it?)

While this monolith is highly robust for a medium enterprise scope, the natural evolution towards large-scale architectures would involve:

1.  **Microservices Migration:** Replacing native Servlets with **Spring Boot** to create independent RESTful APIs.
2.  **Stateless Security:** Shifting from HTTP session management (Cookies) to **JSON Web Tokens (JWT)** to enable horizontal scalability in a cluster.
3.  **Decoupled Front-End:** Moving the presentation layer from JSP to a heavy client framework like **React, Angular, or Next.js**.
4.  **Cloud Orchestration:** Deploying containers using **Kubernetes (K8s)** for auto-scaling and high availability on AWS or GCP.

---
## Author
**Walter Junior Barreto Sanchez**
*Software Engineer & DevOps*

*This Java EE system was developed as a hands-on engineering project to deepen my understanding of backend architecture, reinforce core Java fundamentals, and gain practical enterprise-level experience.*
