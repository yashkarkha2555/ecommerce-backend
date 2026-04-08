Testing
=======

Unit Tests
----------
- `AuthServiceTest`
- `ProductServiceTest`

Run:
`mvn test`

Notes
-----
Test profile uses H2 with `spring.sql.init.mode=never` to avoid running
`data.sql` during tests.
