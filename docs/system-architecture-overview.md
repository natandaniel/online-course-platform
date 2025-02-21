# System Architecture Overview

This architecture adheres to Clean Architecture and the Port–Adapter pattern, ensuring that core business rules remain stable and independent, while still allowing for flexible integration with external technologies.
The architecture is organized into four concentric circles. Each inner circle represents a higher level of abstraction and business policy, while dependencies always point inward. Data crossing boundaries between layers is conveyed via simple Data Transfer Objects (DTOs) or plain Java objects (POJOs), ensuring that inner layers remain isolated from external concerns.

---

## 1. **Entities Layer (Core Domain)**
- **Purpose:**  
  Encapsulate the enterprise-wide critical business rules.  
  This layer contains the domain entities, value objects, and domain events that express the core business logic of the application.

- **Characteristics:**
    - Independent of external frameworks, databases, or UI concerns.
    - Contains high-level business policies and invariants.
    - Least likely to change even if external systems evolve.

- **Example:**  
  Domain objects such as `Course`, `Module`, `Lesson`, `User`, `Enrollment`, `Payment`, etc.

---

## 2. **Use Case Layer (Application Business Rules)**
- **Purpose:**  
  Implement all application-specific business rules by orchestrating interactions between domain entities.  
  This layer defines and executes the use cases of the system without being affected by changes in external systems.

- **Responsibilities:**
    - Coordinate the flow of data between input and output boundaries.
    - Invoke domain entities’ methods to enforce business rules.
    - Use ports (interfaces) to interact with the data layer, external services, etc.

- **Key Principle:**  
  Changes in the use case layer should not affect the core domain entities, and it remains insulated from the details of external frameworks (like databases, UI, or web frameworks).

- **Data Flow:**
    - **Input:** Receives plain Java objects (DTOs) from the Interface Adapters via an InputBoundary.
    - **Output:** Returns results as OutputData objects through an OutputBoundary, which the Presenter then converts into a ViewModel for the UI.

---

## 3. **Interface Adapters Layer**
- **Purpose:**  
  Act as a translator between the external systems (web, database, external services) and the internal application logic.

- **Responsibilities:**
    - **Presentation (MVC):**
        - **Controllers:** Accept and validate user input, package it into plain Java objects (DTOs), and pass it to the use case layer.
        - **Presenters/Views:** Convert OutputData from the use case layer into a ViewModel (e.g., formatted strings, flags) suitable for rendering in the UI.
    - **Persistence Adapters:**
        - Translate between the domain models and the database schema (using repositories and mappers).
    - **External Integrations:**
        - Adapt data formats from external services (e.g., payment gateways, messaging systems) to the internal DTOs.

- **Key Principle:**  
  This layer contains all adapters that convert data to and from the format most convenient for the inner layers and that which is required by external agencies. Only simple DTOs cross the boundaries, ensuring inner layers remain unaware of external details.

---

## 4. **Frameworks & Tools (Outermost Layer)**
- **Purpose:**  
  Provide the technical implementation for aspects such as the web server, database, messaging system, and UI framework.

- **Responsibilities:**
    - **Web Framework:**
        - Spring Boot and related libraries handle HTTP requests and responses.
    - **Database:**
        - PostgreSQL (with JPA) manages persistence.
    - **Containerization & Orchestration:**
        - Docker and Kubernetes manage deployment, scaling, and resilience.
    - **External Services:**
        - Stripe for payment processing, Kafka for messaging, etc.

- **Key Principle:**  
  This layer is entirely external to your core business logic. Changes in technology or external providers (e.g., switching to a different database) should not affect the inner circles. Data is translated into simple DTOs before crossing into the inner layers.

---

## Data Flow Example: User Registration

1. **Frontend:**
    - The web server gathers registration input (e.g., name, email, password) from the user.
    - The Controller packages this input into a DTO and passes it to the **InputBoundary** interface in the Use Case Layer.

2. **Use Case Layer:**
    - The Use Case Interactor receives the DTO, invokes domain entities in the **Entities Layer** (e.g., `User`), and processes business logic.
    - The Interactor then creates an OutputData object representing the result.

3. **Interface Adapters Layer:**
    - The Presenter converts the OutputData into a ViewModel (e.g., a welcome message with formatted data).
    - The Controller returns the ViewModel to the frontend for rendering.

4. **Frameworks & Tools:**
    - The HTTP server (Spring Boot) handles the request/response lifecycle.
    - The Database Adapter (JPA Repository) stores the user information in PostgreSQL.
    - External services (if needed) are invoked and then adapted into the system's DTOs.

---

## Summary

- **Inner Layers (Entities & Use Cases):**  
  Contain the core business logic and remain isolated from external changes.

- **Interface Adapters:**  
  Translate data between the internal domain and external systems.

- **Outermost Layer:**  
  Comprises frameworks and tools that implement the system's technical requirements.

- **Data Transfer:**  
  Simple DTOs pass data between layers, ensuring that inner circles are unaware of external system complexities.

