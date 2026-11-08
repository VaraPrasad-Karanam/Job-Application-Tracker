# Job Application Tracker

A console-based Java application that helps users manage and track their job applications using JDBC and MySQL.

## Features

- Add new job applications.
- View all job applications.
- Search applications by ID.
- Update application status.
- Delete job applications.
- Filter applications by status.
- View application summary by status.
- Input validation for menu choices, dates and application statuses.
- Persistent data storage using MySQL.

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- MySQL Connector/J
- Visual Studio Code

## Project Structure

```text
JobApplicationTracker/
│
├── Main.java
├── JobApplication.java
├── JobApplicationDAO.java
├── DBConnection.java
│
└── lib/
    └── MySQL Connector/J
```

## Database Setup

Create the database:

```sql
CREATE DATABASE job_tracker;
USE job_tracker;
```

Create the applications table:

```sql
CREATE TABLE applications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100) NOT NULL,
    job_role VARCHAR(100) NOT NULL,
    applied_date DATE,
    status VARCHAR(30) DEFAULT 'Applied'
);
```

## Application Statuses

- Applied
- Assessment
- Interview
- Selected
- Rejected

## How to Run

1. Install Java JDK.
2. Install MySQL Server.
3. Create the database and table using the SQL commands above.
4. Add MySQL Connector/J to the Java project classpath.
5. Configure your MySQL username and password in `DBConnection.java`.
6. Run `Main.java`.

## Concepts Practised

- Object-Oriented Programming
- Encapsulation
- Classes and Objects
- Constructors and Getters
- JDBC Connectivity
- PreparedStatement
- ResultSet
- CRUD Operations
- ArrayList and List
- Exception Handling
- SQL Aggregate Functions
- DAO (Data Access Object) Pattern

## Future Enhancements

- Graphical user interface.
- Login and user authentication.
- Export application records to CSV or Excel.
- Application reminders and notifications.

## Author

Java Developer | Computer Science Graduate