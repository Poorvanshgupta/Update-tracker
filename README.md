\# Update Tracker



A Spring Boot backend application for managing users, projects, and daily work updates with JWT-based authentication and role-based access control.



The application allows administrators to create users and projects, assign users to projects, and allows users to submit and retrieve their work updates.



\---



\## Features



\### Authentication \& Authorization

\- JWT-based authentication

\- Role-based access control

\- ADMIN and USER roles

\- Password hashing using BCrypt

\- Protected REST APIs



\### User Management

\- Admin can create users

\- Supports ADMIN and USER roles

\- Users can be assigned to multiple projects



\### Project Management

\- Admin can create projects

\- Admin can assign projects to users

\- Users can create updates only for projects assigned to them



\### Work Updates

\- Users can create daily work updates

\- Users can retrieve their own updates

\- Search updates by:

&#x20; - Username

&#x20; - Project

&#x20; - Date range



\### Validation \& Error Handling

\- Request validation using Jakarta Bean Validation

\- Centralized exception handling using `@RestControllerAdvice`

\- Standard HTTP status codes for API errors



\---



\## Tech Stack



| Technology | Usage |

|---|---|

| Java 17 | Programming language |

| Spring Boot | Backend framework |

| Spring Security | Authentication \& authorization |

| JWT | Stateless authentication |

| Spring Data JPA | Database access |

| Hibernate | ORM |

| H2 Database | Development database |

| Maven | Build \& dependency management |

| REST API | Client-server communication |

| Postman | API testing |



\---



\## Architecture



The application follows a layered monolithic architecture.



```text

Client / Postman

&#x20;      |

&#x20;      v

REST Controllers

&#x20;      |

&#x20;      v

Service Layer

&#x20;      |

&#x20;      v

Repository Layer

&#x20;      |

&#x20;      v

H2 Database

```



\### Application Layers



\*\*Controller Layer\*\*



Handles HTTP requests and responses.



\*\*Service Layer\*\*



Contains business logic such as:



\- User creation

\- Project creation

\- Project assignment

\- Update creation

\- Project access validation

\- Searching updates



\*\*Repository Layer\*\*



Uses Spring Data JPA to communicate with the database.



\*\*Security Layer\*\*



Handles:



\- JWT generation

\- JWT validation

\- Authentication

\- Authorization

\- Security context population

