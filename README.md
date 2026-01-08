# Leave Service

A microservice-based leave management system built with Spring Boot that handles employee leave requests, approvals, and balance tracking.

## Overview

Leave Service is part of a microservices architecture for HR management. It provides REST APIs for managing employee leave requests, approvals workflows, and leave balance calculations. The service integrates with an authentication service for secure access control.

## Features

- **Leave Request Management**: Submit, view, and manage leave requests
- **Approval Workflow**: Multi-level approval system for managers and HR
- **Leave Balance Tracking**: Real-time calculation of available leave days
- **Leave Types Support**: Annual leave, sick leave, unpaid leave, and custom types
- **Role-Based Access Control**: Different permissions for employees, managers, and HR admins
- **JWT Authentication**: Secure API endpoints with token-based authentication
- **RESTful API**: Clean, documented endpoints for easy integration

## Tech Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Security**: Spring Security + JWT (jjwt 0.11.5)
- **Database**: PostgreSQL (production) / H2 (development)
- **ORM**: Spring Data JPA
- **Validation**: Spring Boot Validation
- **API Documentation**: Swagger/OpenAPI
- **Cloud**: Spring Cloud Config for distributed configuration
- **Build Tool**: Maven

## Architecture

This service follows microservices architecture principles:
- Stateless REST APIs
- JWT-based authentication (tokens validated from Auth Service)
- Database per service pattern
- Cloud-native configuration management

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+ (for production)
- Auth Service running (for authentication)

## Getting Started

### 1. Clone the repository
```bash
git clone <your-repo-url>
cd leave-service
```

### 2. Configure database
Update `application.properties` or `application.yml`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/leave_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Configure JWT secret
Set the JWT secret key (should match Auth Service):
```properties
jwt.secret=your_secret_key_here
```

### 4. Run the application
```bash
mvn spring-boot:run
```

The service will start on `http://localhost:8081` (default port)

## API Endpoints

### Leave Requests
- `POST /api/leaves` - Submit a new leave request
- `GET /api/leaves` - Get all leave requests (filtered by role)
- `GET /api/leaves/{id}` - Get specific leave request
- `PUT /api/leaves/{id}` - Update leave request (employee)
- `DELETE /api/leaves/{id}` - Cancel leave request

### Leave Approvals (Manager/HR)
- `PUT /api/leaves/{id}/approve` - Approve a leave request
- `PUT /api/leaves/{id}/reject` - Reject a leave request
- `GET /api/leaves/pending` - Get pending leave requests

### Leave Balance
- `GET /api/leaves/balance` - Get current user's leave balance
- `GET /api/leaves/balance/{userId}` - Get specific user's balance (HR only)

## User Roles

- **EMPLOYEE**: Submit and view own leave requests
- **MANAGER**: Approve/reject team member requests
- **HR_ADMIN**: Full access to all leave records and balances

## Database Schema

Key entities:
- **LeaveRequest**: Leave request details (dates, type, status, reason)
- **LeaveBalance**: User leave balance by type
- **LeaveType**: Configurable leave types (Annual, Sick, etc.)
- **ApprovalHistory**: Audit trail of approvals/rejections

## Security

All endpoints except health checks require JWT authentication. Include the token in requests:
```
Authorization: Bearer <your-jwt-token>
```

Tokens are validated using the shared secret key configured in both Auth and Leave services.

## Testing

Run unit and integration tests:
```bash
mvn test
```

## Built With

- Spring Boot - Application framework
- Spring Security - Authentication and authorization
- Spring Data JPA - Database access
- PostgreSQL - Production database
- Lombok - Boilerplate code reduction
- ModelMapper - Object mapping
- Problem Spring Web - RFC 7807 error handling

## Future Enhancements

- Email notifications for leave approvals
- Leave calendar integration
- Holiday calendar management
- Leave carryover policies
- Reporting and analytics dashboard

## License

This project is part of a learning portfolio.

## Author

Built as part of a microservices HR management system.
