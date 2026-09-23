# Update Tracker

A reconstructed Spring Boot backend based on the Update Tracker design we developed together. This is a reconstruction, not the original source archive.

## Features
- Admin/User roles
- JWT authentication
- Admin creates users and projects
- Admin assigns users to projects
- Users create project-specific work updates
- Search updates by username/project/date range
- User's own update history
- Admin reporting by user/project
- Validation and global exception handling
- H2 database and console for local development
- JUnit/Spring Boot context test

## Stack
Java 17, Spring Boot 3.5.6, Spring Security, JWT (JJWT), Spring Data JPA, H2, Maven, JUnit 5.

## Run
```bash
mvn clean spring-boot:run
```
Application: http://localhost:8080
H2 console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:updatetracker
User: sa
Password: (empty)

## API flow
1. Create an initial admin user directly in the database or add a seed mechanism.
2. Login with `POST /api/auth/login`.
3. Send `Authorization: Bearer <token>` on protected calls.
4. Admin creates users/projects and assigns project access.
5. User creates updates with `POST /api/updates`.

### Example login
```json
POST /api/auth/login
{
  "username": "admin",
  "password": "password"
}
```

### Create update
```json
POST /api/updates
{
  "updateDate": "2026-09-23",
  "projectId": 1,
  "content": "Implemented backend validation and fixed production issue."
}
```

## Important
For production, replace `app.jwt.secret`, use a persistent database, add migrations (Flyway/Liquibase), stronger DTO responses, auditing, and externalized secrets.
