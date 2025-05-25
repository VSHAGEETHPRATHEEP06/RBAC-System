# RBAC-System
# Laboratory Role-Based Access Control System

A comprehensive Role-Based Access Control (RBAC) system designed for laboratory management. This system provides secure authentication and fine-grained access control for different roles within a laboratory environment.

## Features

- **JWT-based Authentication**: Secure token-based authentication with token blacklisting for logout
- **Role-Based Access Control**: Granular permissions for different user roles
- **User Management**: Create, update, and manage users with different roles
- **Laboratory Operations**: Endpoints for lab test requests, reports, and analytics
- **First-time Login Flow**: Password reset requirement for first-time users

## Technical Stack

- **Backend**: Java 17, Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: PostgreSQL
- **Build Tool**: Maven

## System Architecture

The system is built on a layered architecture:

- **Controllers**: Handle HTTP requests and implement API endpoints
- **Services**: Implement business logic and transaction management
- **Repositories**: Interface with the database using Spring Data JPA
- **Security**: JWT-based authentication and authorization filters

## Role & Permission Structure

### Roles

1. **ADMIN**: Complete access to all system features
2. **LAB_MANAGER**: Access to analytics, view and update test requests and reports
3. **LAB_TECHNICIAN**: Create and view test requests and reports

### Permission Modules

1. **LAB_ANALYTICS**: Laboratory performance metrics and statistics
2. **TEST_REQUEST**: Patient test request management
3. **TEST_REPORT**: Test results and report management
4. **USER_MANAGEMENT**: User creation and management

Each module supports the following actions: VIEW, CREATE, UPDATE, DELETE

## API Endpoints

### Authentication
- `POST /api/auth/login`: Authenticate user and receive JWT token
- `POST /api/auth/logout`: Invalidate JWT token

### User Management
- `POST /api/users`: Create new user (requires USER_MANAGEMENT:CREATE permission)
- `PUT /api/users/{id}`: Update user profile
- `PUT /api/users/{id}/role`: Update user role (requires USER_MANAGEMENT:UPDATE permission)
- `GET /api/users/{id}`: Get user details
- `PUT /api/users/reset-password`: Reset password (for first login or password change)

### Laboratory Operations
- `GET /api/lab/analytics`: Get laboratory analytics (requires LAB_ANALYTICS:VIEW permission)
- `POST /api/lab/test-request`: Create new test request (requires TEST_REQUEST:CREATE permission)
- `GET /api/lab/test-request`: Get test requests (requires TEST_REQUEST:VIEW permission)

## Getting Started

### Prerequisites
- Java 17 or higher
- PostgreSQL
- Maven

### Configuration
The application is configured using `application.yml` with the following key settings:
- Database connection properties
- JWT secret and expiration time
- Server port (default: 8081)

### Default Admin User
On first startup, the system creates a default admin user:
- Username: admin
- Password: admin123

## Security Considerations
- JWT tokens are secured with a strong secret key
- Tokens are blacklisted on logout
- Passwords are encrypted using BCrypt
- Field validation is implemented for all user inputs
