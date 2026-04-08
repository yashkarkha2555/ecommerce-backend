Security
========

- JWT authentication with role-based access (USER, ADMIN).
- Passwords stored using BCrypt.
- Public endpoints are limited to auth and product/category reads.
- Admin routes are restricted under `/api/admin/**`.

JWT
---
The token includes `sub` (email) and `role` claims.

Configuration
-------------
Set `JWT_SECRET` in the environment or via `application.properties`.
