# Food Ordering Backend

A RESTful API backend built with Spring Boot for a full-featured food ordering platform. It handles all server-side logic including user registration and login with JWT-based authentication, role-based access control for ADMIN and CUSTOMER roles, and complete CRUD operations for food items, categories, users, carts, orders, and payments.

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

## Project Structure

```text
food-ordering-backend/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/lk/ijse/food_ordering_backend/
│   │   │   ├── FoodOrderingBackendApplication.java
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── CartController.java
│   │   │   │   ├── CategoryController.java
│   │   │   │   ├── FoodController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   ├── PaymentController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dao/
│   │   │   │   ├── CartDao.java
│   │   │   │   ├── CartItemDao.java
│   │   │   │   ├── CategoryDao.java
│   │   │   │   ├── FoodItemDao.java
│   │   │   │   ├── OrderDao.java
│   │   │   │   ├── OrderItemDao.java
│   │   │   │   ├── PaymentDao.java
│   │   │   │   └── UserDao.java
│   │   │   ├── dto/
│   │   │   │   ├── AuthDTO.java
│   │   │   │   ├── CartDTO.java
│   │   │   │   ├── CartItemDTO.java
│   │   │   │   ├── CategoryDTO.java
│   │   │   │   ├── FoodItemDTO.java
│   │   │   │   ├── OrderDTO.java
│   │   │   │   ├── OrderItemDTO.java
│   │   │   │   ├── PaymentDTO.java
│   │   │   │   └── UserDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── Cart.java
│   │   │   │   ├── CartItem.java
│   │   │   │   ├── Category.java
│   │   │   │   ├── FoodItem.java
│   │   │   │   ├── FoodItemStatus.java
│   │   │   │   ├── Order.java
│   │   │   │   ├── OrderItem.java
│   │   │   │   ├── OrderStatus.java
│   │   │   │   ├── Payment.java
│   │   │   │   ├── PaymentStatus.java
│   │   │   │   ├── Role.java
│   │   │   │   └── User.java
│   │   │   ├── exception/
│   │   │   │   ├── DataNotFoundException.java
│   │   │   │   ├── DuplicateEntryException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── security/
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   ├── JWTFilter.java
│   │   │   │   └── JWTUtil.java
│   │   │   ├── service/
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── CartService.java
│   │   │   │   ├── CategoryService.java
│   │   │   │   ├── FoodItemService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   ├── PaymentService.java
│   │   │   │   ├── UserService.java
│   │   │   │   └── impl/
│   │   │   │       ├── AuthServiceImpl.java
│   │   │   │       ├── CartServiceImpl.java
│   │   │   │       ├── CategoryServiceImpl.java
│   │   │   │       ├── FoodItemServiceImpl.java
│   │   │   │       ├── OrderServiceImpl.java
│   │   │   │       ├── PaymentServiceImpl.java
│   │   │   │       └── UserServiceImpl.java
│   │   │   └── util/
│   │   │       ├── AppConstants.java
│   │   │       └── CustomStatus.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application.properties.example
│   │       ├── data.sql
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/lk/ijse/food_ordering_backend/
│           └── FoodOrderingBackendApplicationTests.java
└── target/
```

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

### Configuration

Copy the example config and fill in your values:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Then update these in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodorder_db
spring.datasource.username=root
spring.datasource.password=your_password
app.jwt.secret=your_secret_key
```

### Database Setup

1. Create an empty database in MySQL:

```sql
CREATE DATABASE foodorder_db;
```

2. Start the backend — JPA will auto-create all tables:

```bash
mvn spring-boot:run
```

3. Sample data is loaded automatically from `data.sql` on first run.

> [!IMPORTANT]
> 4. After the first successful run, change in `application.properties`:
> ```properties
> spring.sql.init.mode=never
> ```
> `spring.jpa.hibernate.ddl-auto=update` ensures your data is never
> wiped on restart. Tables and data are preserved across all restarts.

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
| DELETE | /api/users/{id} | ADMIN, CUSTOMER |

---

## Frontend

This backend connects to the [Food Ordering Frontend](https://github.com/Ama-Dombawela/food-ordering-frontend) — a Vite + React + TypeScript frontend