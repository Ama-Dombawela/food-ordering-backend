# Food Ordering Backend

A RESTful API backend built with Spring Boot for a full-featured food ordering platform. It handles all server-side logic including user registration and login with JWT-based authentication, role-based access control for ADMIN and CUSTOMER roles, and complete CRUD operations for food items, categories, users, carts, orders, and payments.

---

## Tech Stack

| Technology | Details |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.6 |
| Spring Data JPA | ORM & database access |
| Spring Security | JWT-based auth |
| MySQL | Relational database |
| Lombok | Boilerplate reduction |

---

## Features

- **Authentication** — Secure register and login endpoints using JWT tokens. Tokens carry user identity and role claims used across all protected routes.
- **Role-based Access Control** — Two roles: `ADMIN` for full platform management and `CUSTOMER` for browsing, ordering, and account access. Route-level enforcement via Spring Security.
- **User Management** — Full CRUD on user accounts. Admins can delete users; customers can view and update their own profile.
- **Category Management** — Admins can create, update, and delete food categories. Both roles can browse categories.
- **Food Item Management** — Admins manage the food catalog including name, description, price, category, and availability status (`AVAILABLE` / `OUT_OF_STOCK`). Customers can browse and filter by category or status.
- **Cart Management** — Per-user cart with the ability to add items, remove individual items, or clear the entire cart.
- **Order Management** — Customers can place orders from their cart. Both roles can view and update order status (`PLACED`, `PREPARING`, `DELIVERED`, `CANCELLED`).
- **Payment Management** — Payment records are created per order and support status tracking (`PENDING`, `COMPLETED`, `FAILED`).

---

## Getting Started

### Prerequisites

- Java 21
- Maven 3.x
- MySQL server

### Installation & Run

```bash
git clone https://github.com/Ama-Dombawela/food-ordering-backend.git
cd food-ordering-backend
```

Configure your database and JWT secret in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodorder_db
spring.datasource.username=root
spring.datasource.password=your_password
app.jwt.secret=your_secret_key
```

Then build and run:

```bash
mvn clean package
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

---

## API Endpoints

| Method | Endpoint | Access |
|---|---|---|
| POST | /api/auth/register | Public |
| POST | /api/auth/login | Public |
| GET | /api/cart/{userId} | CUSTOMER |
| POST | /api/cart/{userId}/items | CUSTOMER |
| DELETE | /api/cart/{userId}/items/{itemId} | CUSTOMER |
| DELETE | /api/cart/{userId} | CUSTOMER |
| POST | /api/categories | ADMIN |
| GET | /api/categories | ADMIN, CUSTOMER |
| GET | /api/categories/{id} | ADMIN, CUSTOMER |
| PUT | /api/categories/{id} | ADMIN |
| DELETE | /api/categories/{id} | ADMIN |
| POST | /api/foods | ADMIN |
| PUT | /api/foods/{id} | ADMIN |
| DELETE | /api/foods/{id} | ADMIN |
| GET | /api/foods | ADMIN, CUSTOMER |
| GET | /api/foods/{id} | ADMIN, CUSTOMER |
| GET | /api/foods/category/{categoryId} | ADMIN, CUSTOMER |
| GET | /api/foods/status/{status} | ADMIN, CUSTOMER |
| POST | /api/orders/{userId} | ADMIN, CUSTOMER |
| GET | /api/orders/{id} | ADMIN, CUSTOMER |
| GET | /api/orders/user/{userId} | ADMIN, CUSTOMER |
| PUT | /api/orders/{id}/status | ADMIN, CUSTOMER |
| DELETE | /api/orders/{id} | ADMIN, CUSTOMER |
| POST | /api/payments | ADMIN, CUSTOMER |
| GET | /api/payments/{id} | ADMIN, CUSTOMER |
| GET | /api/payments/order/{orderId} | ADMIN, CUSTOMER |
| PUT | /api/payments/{id}/status | ADMIN, CUSTOMER |
| GET | /api/users | ADMIN, CUSTOMER |
| GET | /api/users/{id} | ADMIN, CUSTOMER |
| PUT | /api/users/{id} | ADMIN, CUSTOMER |
| DELETE | /api/users/{id} | ADMIN |

---

## Frontend

This backend connects to the [Food Ordering Frontend](https://github.com/Ama-Dombawela/food-ordering-frontend) — a Vite + React + TypeScript frontend