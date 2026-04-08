# 🛒 Complete E-commerce Backend System

## 📌 Project Overview

This project is a full-featured e-commerce backend built using Spring Boot. It provides RESTful APIs for managing products, users, shopping carts, orders, payments, and administrative operations.

## 🎯 Objectives

* Build a scalable backend using Spring Boot
* Implement RESTful APIs using Spring MVC
* Use Spring Data JPA for database operations
* Secure APIs using Spring Security with JWT
* Follow clean layered architecture (Controller → Service → Repository)

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* PostgreSQL
* Spring Security (JWT)
* Maven
* Swagger/OpenAPI

---

## 🚀 Features

* User Registration & Login (JWT Authentication)
* Product & Category Management
* Shopping Cart Functionality
* Order Processing System
* Admin Management APIs
* Product Search & Filtering
* Input Validation & Exception Handling

---

## 🏗️ Project Structure

```
src/main/java/com/ecommerce
  config/       - Configuration classes
  controller/   - REST controllers
  service/      - Business logic
  repository/   - JPA repositories
  model/        - Entities
  dto/          - Data transfer objects
  security/     - JWT authentication
  exception/    - Global exception handling

src/main/resources
  application.properties
  schema.sql
  data.sql
```

---

## 🛠️ Setup Instructions

### 1️⃣ Clone Repository

```
git clone https://github.com/yourusername/ecommerce-backend.git
cd ecommerce-backend
```

### 2️⃣ Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:yourpassword}
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Set Environment Variables

Example:

```
DB_USERNAME=postgres
DB_PASSWORD=yourpassword
JWT_SECRET=your-secret-key
```

### 4️⃣ Run Application

```
mvn spring-boot:run
```

### 5️⃣ Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📡 API Endpoints

### 🔐 Authentication

* POST /api/auth/register
* POST /api/auth/login

### 📦 Products

* GET /api/products
* GET /api/products/{id}
* POST /api/products (Admin)
* PUT /api/products/{id} (Admin)
* DELETE /api/products/{id} (Admin)

### 🛒 Cart

* POST /api/cart/add
* GET /api/cart

### 📦 Orders

* POST /api/orders
* GET /api/orders/{id}

---

## 🔐 Security Implementation

* JWT-based authentication
* Role-based authorization (USER, ADMIN)
* Password encryption using BCrypt
* Secure REST APIs using Spring Security

---

## 📷 Screenshots



* Application running
 ![](D:\Intership Project\Complete E-commerce Backend System\docs\ScreenShots\Screenshot 2026-03-17 161753.png)
* Swagger UI
![](D:\Intership Project\Complete E-commerce Backend System\docs\ScreenShots\Screenshot 2026-03-17 161821.png)
![](D:\Intership Project\Complete E-commerce Backend System\docs\ScreenShots\Screenshot 2026-03-17 161853.png)
* Database tables
![](D:\Intership Project\Complete E-commerce Backend System\docs\ScreenShots\Screenshot 2026-03-17 162144.png)

---

## 🧪 Testing

Run tests:

```
mvn test
```

---

## 📄 API Documentation

Swagger UI available at:
http://localhost:8080/swagger-ui/index.html

---

## 📬 Postman Collection

Available in:

```
postman/postman_collection.json
```

---

## 🐳 Docker (Optional)

Run using:

```
docker compose up --build
```

---

## 📦 Environment Variables Example

Create a `.env` file using `.env.example` as a reference:

```
DB_USERNAME=postgres
DB_PASSWORD=yourpassword
JWT_SECRET=your-secret-key
```

Configure environment variables:

```
DB_USERNAME=postgres
DB_PASSWORD=yourpassword
JWT_SECRET=your-secret-key
```

---

## ✅ Internship Requirements Covered

* REST APIs using Spring MVC ✔️
* JPA entities with relationships ✔️
* Spring Data JPA ✔️
* Service layer architecture ✔️
* Validation & error handling ✔️
* Spring Security ✔️
* Cart & Order system ✔️
* Product search & filtering ✔️

---

## 📌 Conclusion

This project demonstrates the implementation of a complete e-commerce backend system using Spring Boot, covering REST APIs, database integration, security, and modern backend development practices.
