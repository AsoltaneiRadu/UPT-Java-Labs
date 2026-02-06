# ☕ Java & Spring Boot Development Portfolio

### Politehnica University of Timișoara (UPT)

This repository documents my journey and progression in Java Backend Development. It contains a collection of projects evolving from core programming concepts to building scalable applications connected to databases using the Spring Framework.

---

## 🛠️ Tech Stack
* **Core Language:** Java 17+
* **Frameworks:** Spring Boot, Spring Data JPA
* **Databases:** MySQL 
* **Build Tools:** Maven / Gradle
* **IDE:** IntelliJ IDEA Ultimate

## 📚 Learning Path & Modules

The repository is organized to reflect the step-by-step mastery of backend engineering:

### 1. Java Core & OOP Mastery
Solidifying the foundation of software engineering.
* **Syntax & Logic:** Algorithms, data types, and control flow.
* **OOP Pillars:** Deep dive into Encapsulation, Inheritance, Polymorphism, and Abstraction.
* **Collections Framework:** Efficient data manipulation using `Lists`, `Sets`, and `Maps`.

### 2. Database Connectivity (JDBC & Persistence)
Bridging the gap between application code and persistent storage.
* **JDBC:** Writing raw SQL queries within Java to understand the low-level connection logic.
* **CRUD Operations:** Implementing Create, Read, Update, and Delete functionalities.
* **Database Design:** Designing normalized table structures and relationships (One-to-Many, Many-to-Many).

### 3. Spring Boot Applications
Transitioning to enterprise-level development.
* **Architecture:** Implementing the **MVC pattern** (Model-View-Controller) and **Layered Architecture** (Controller -> Service -> Repository).
* **Dependency Injection (DI):** Using Spring's Inversion of Control (IoC) container.
* **REST APIs:** Building endpoints to serve data to frontend clients.
* **ORM (Object-Relational Mapping):** Using Hibernate/Spring Data JPA to map Java classes directly to database tables without writing boilerplate SQL.



---

## 📂 Repository Structure

| Module/Folder | Key Technologies | Description |
| :--- | :--- | :--- |
| `01-Java-Basics` | Java SE | Intro to syntax and algorithms. |
| `02-OOP-Principles` | Java, Interfaces | Polymorphism and abstract classes examples. |
| `03-Collections` | ArrayList, HashMap | Data structures and sorting algorithms. |
| `04-Database-Connect` | JDBC, SQL | Direct database connection and manual query execution. |
| `05-Spring-Boot-App` | Spring Boot, Hibernate | A full web application connecting to a database. |

*(Note: Adjust folder names above to match your actual structure)*

## 🚀 How to Run the Spring Boot Project
1. Navigate to the Spring Boot folder.
2. Configure the `application.properties` file with your local database credentials.
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
