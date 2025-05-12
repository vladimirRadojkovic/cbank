# C-Bank Banking Application

A Java-based banking application that has been migrated from NetBeans/Ant to Maven and Docker.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Docker and Docker Compose
- Git

### Clone the Repository

```bash
git clone https://github.com/yourusername/cbank.git
cd cbank
```

### Build the Application

```bash
mvn clean package
```

### Run with Docker Compose

```bash
docker-compose up -d
```

This will start:
- MySQL database container
- Tomcat web container with the deployed application

### Access the Application

Open your browser and navigate to:
```
http://localhost:8080/
```

## Test Accounts

The application comes with predefined users for testing:

| Username | Password  | Role(s)       |
|----------|-----------|---------------|
| admin    | admin123  | ADMIN         |
| user     | pass123   | USER          |
| dualuser | dual123   | USER and ADMIN |

## Features

- User authentication and role-based authorization
- Account management
- Transaction history
- Money transfers
- Credit management
- Administration panel

## Application Structure

- Maven-based project structure
- Spring MVC for web layer
- Spring Security for authentication and authorization
- Hibernate ORM for database access
- MySQL for data storage
- Tomcat for application server
- Docker for containerization

## Technologies Used

- Java 8
- Spring Framework 4.3.2
- Spring Security 4.1.3
- Hibernate 5.2.1
- MySQL 8.0
- Maven 3.8.6
- Tomcat 9.0
- Docker & Docker Compose

## Notes

- This application has been migrated from a legacy NetBeans/Ant project to a modern Maven structure
- XML configurations have been replaced with Java-based configurations
- Transaction management has been updated to use Spring's declarative transactions