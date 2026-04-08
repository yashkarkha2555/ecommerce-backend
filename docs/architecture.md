Architecture
============

Layers
------
- Controller: REST endpoints
- Service: business logic and orchestration
- Repository: persistence (Spring Data JPA)
- Model/DTO: JPA entities and transport objects

Key Flows
---------
- Auth: register/login -> JWT
- Cart: add/update/remove items -> cart totals
- Order: create from cart -> stock update
- Payment: create payment -> order marked PAID
