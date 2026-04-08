API Overview
===========

Public Endpoints
----------------
- POST /api/auth/register
- POST /api/auth/login
- GET /api/products
- GET /api/products/{id}
- GET /api/products/search
- GET /api/products/filter
- GET /api/products/category/{id}
- GET /api/categories

User Endpoints (JWT)
--------------------
- GET /api/users/profile
- GET /api/carts
- POST /api/carts/items
- PUT /api/carts/items
- DELETE /api/carts/items/{id}
- POST /api/orders
- GET /api/orders
- GET /api/orders/{id}
- POST /api/payments
- GET /api/payments/order/{orderId}

Admin Endpoints (ADMIN)
-----------------------
- GET /api/admin/users
- PUT /api/admin/users/{id}/role
- GET /api/admin/orders
- PUT /api/admin/orders/{id}/status
- GET /api/admin/payments
- GET /api/admin/reports/summary
- PUT /api/admin/inventory/{productId}
