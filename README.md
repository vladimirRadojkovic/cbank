# CBank Web Application

## Overview

This is a banking web application built with Spring Framework, Hibernate, and MySQL. The project was originally built with NetBeans and Ant, but has been migrated to Maven for better dependency management and build process.

## Project Structure

- `src/java`: Java source files
- `src/conf`: Configuration files
- `web`: Web resources (HTML, JSP, CSS, JS, etc.)
- `lib`: External libraries (now managed by Maven)

## Technologies Used

- Spring Framework 4.0.0
- Spring Security 3.2.3
- Hibernate 4.3.5
- MySQL Connector
- Servlet API 3.1.0
- JSP/JSTL
- Logback 1.1.2

## Building the Project

### Prerequisites

- Java JDK 8+
- Maven 3.6+
- MySQL Database

### Maven Build

```bash
# Clone the repository
git clone <repository-url>
cd cbank

# Build the project
mvn clean package

# The WAR file will be generated in the target directory
# target/cbank.war
```

### Running the Application

#### Using Embedded Jetty (for development)

The project includes the Jetty Maven plugin for easy development:

```bash
# Run with Jetty
mvn jetty:run

# Access the application at http://localhost:8080/cbank
```

#### Using External Server

Deploy the generated WAR file to a Servlet container like Tomcat, Jetty, or GlassFish.

```bash
# Example for Tomcat (assuming Tomcat is installed and in your PATH)
cp target/cbank.war $TOMCAT_HOME/webapps/
```

## Database Setup and Configuration

### Initial Setup
* Create a "bank" database in MySQL
* Manually insert at least one record in the "Korisnik" table
* In the "KorisnikRoles" table, add at least one role e.g., "ROLE_USER". If you want to be an admin as well, add both "ROLE_ADMIN" and "ROLE_USER"
* Edit the database connection settings (see below)

### Database Configuration

The database connection is configured in `web/WEB-INF/applicationContext.xml`. Update this file with your database connection details before deploying the application:

```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/bank" />
    <property name="username" value="your_username" />
    <property name="password" value="your_password" />
</bean>
```

## Migration from Ant to Maven

This project was originally built with Ant. The migration to Maven involved:

1. Creating a Maven `pom.xml` file
2. Resolving dependencies with proper Maven coordinates
3. Adjusting the project structure to Maven conventions
4. Configuring the Maven War plugin to use the existing web resources

### Key Maven Configuration

- The project uses the standard Maven WAR packaging
- The `maven-war-plugin` is configured to use the existing `web` directory for web resources
- All dependencies are now managed in the `pom.xml` file instead of JARs in the `lib` directory
- Embedded Jetty server for development and testing

## License

[Your License Information]