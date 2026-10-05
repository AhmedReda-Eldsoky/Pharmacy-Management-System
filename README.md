```markdown
# Pharmacy Management System

A Java-based console application designed to manage pharmacy operations, products, and sales transactions. Built following software engineering best practices, this project utilizes the Data Access Object (DAO) pattern, Object-Oriented Programming (OOP) principles, and JDBC for efficient interaction with a MySQL database.

```

---

## Features

* **Product Inventory Management**
* Add new pharmaceutical products with specific names, prices, and stock quantities.
* Retrieve and display the complete product inventory.
* Search for products by unique Product ID.
* Granular updates for existing product records (modify name, price, or stock levels).
* Remove products from the database.


* **Sales Processing**
* Record sales transactions associated with customer names and total amounts.
* Track transaction creation timestamps automatically via database integration.
* Retrieve complete sales history and receipts.


* **Data Integrity & Robustness**
* Input validation and exception handling for command-line navigation.
* Automatic resource management using `try-with-resources` to prevent database memory leaks.
* Protection against SQL injection using parametrized `PreparedStatement` queries.



---

## System Architecture

The project is structured into three clean, decoupled layers to ensure maintainability and separation of concerns:

* `com.pharmacy.model`: Contains POJO entities (`Product`, `Sale`) encapsulating state and business attributes.
* `com.pharmacy.dao`: Contains DAO interfaces (`ProductDao`, `SaleDao`), JDBC implementation classes (`ProductDaoImpl`, `SaleDaoImpl`), and the database connection manager (`DBConnection`).
* `com.pharmacy.main`: Holds the application entry point (`Main`) and interactive CLI user interface.

---

## Tech Stack

* **Language:** Java 17+
* **Database:** MySQL
* **Database Access:** JDBC (Java Database Connectivity)
* **Build Tool:** Apache Maven

---

## Prerequisites

Before running the application, ensure you have the following installed:

* JDK 17 or higher
* Apache Maven
* MySQL Server & phpMyAdmin (or MySQL Workbench)

---

## Database Setup

1. Start your MySQL server.
2. Create a new database named `pharmacy_db`:
```sql
CREATE DATABASE pharmacy_db;
USE pharmacy_db;

```


3. Create the required tables (`products` and `sales`):
```sql
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    total_amount DOUBLE NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

```


4. Configure database credentials inside `src/main/java/com/pharmacy/dao/DBConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/pharmacy_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

```



---

## Installation & Execution

1. Clone the repository:
```bash
git clone [https://github.com/AhmedReda-Eldsoky/Pharmacy-Management-System.git](https://github.com/AhmedReda-Eldsoky/Pharmacy-Management-System.git)
cd Pharmacy-Management-System

```


2. Build the project using Maven:
```bash
mvn clean compile

```


3. Run the application:
```bash
mvn exec:java -Dexec.mainClass="com.pharmacy.main.Main"

```



---

## Author

**Ahmed Reda Eldsoky**

* GitHub: [AhmedReda-Eldsoky](https://www.google.com/search?q=https://github.com/AhmedReda-Eldsoky)

```

```
