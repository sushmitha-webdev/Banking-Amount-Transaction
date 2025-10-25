# Simple JDBC Banking Transaction

A console-based Java application demonstrating a basic banking transaction (fund transfer) using JDBC (Java Database Connectivity). This program connects to a MySQL database, performs updates within a transaction, and handles user input for transfers between "employee" accounts.

## Features

*   **JDBC Integration:** Connects to a MySQL database to manage employee salaries as account balances.
*   **Transaction Management:** Uses `setAutoCommit(false)`, `commit()`, and `rollback()` for atomic transfers, ensuring data integrity.
*   **Fund Transfer:** Allows a user to transfer a specified amount between two employee accounts.
*   **Balance Check:** Verifies sender's balance before allowing a transfer.
*   **User/Receiver Existence Check:** Ensures both sender and receiver exist in the database.
*   **Confirmation Prompt:** Asks for user confirmation before committing the transaction.
*   **Error Handling:** Catches common JDBC exceptions (`SQLException`, `ClassNotFoundException`).

## Prerequisites

Before running this application, ensure you have:

*   **MySQL Server:** Running on `localhost:3306`.
*   **Database:** A database named `employee01`.
*   **Table:** A table named `employee` with at least columns `name` (VARCHAR) and `salary` (INT).
*   **MySQL JDBC Driver:** Added to your project's classpath (e.g., through Maven, Gradle, or by adding the `.jar` directly in Eclipse).

    Example `employee` table structure:
    ```sql
    CREATE DATABASE employee01;
    USE employee01;
    CREATE TABLE employee (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        salary INT NOT NULL
    );
    INSERT INTO employee (name, salary) VALUES ('Alice', 10000), ('Bob', 5000);
    ```

## Configuration

Update the following constants in `Banking.java` with your database credentials:

*   `url = "jdbc:mysql://localhost:3306/employee01"`
*   `name = "root"`
*   `password = "Sushmitha@123"`

## How to Run in Eclipse

1.  **Create a New Java Project:**
    *   `File > New > Java Project`.
    *   Name it `JDBCBankingApp` (or similar).
    *   Click `Finish`.

2.  **Create a New Package:**
    *   Right-click `src` folder > `New > Package`.
    *   Name it `com.tap.p1` (as specified in the code).
    *   Click `Finish`.

3.  **Create the `Banking.java` Class:**
    *   Right-click the `com.tap.p1` package > `New > Class`.
    *   Name it `Banking`.
    *   Click `Finish`.

4.  **Paste Your Code:**
    *   Open `Banking.java` and paste the entire provided Java code into it.
    *   Save the file (`Ctrl+S`).

5.  **Add MySQL JDBC Driver to Build Path:**
    *   Right-click your project (`JDBCBankingApp`) > `Properties`.
    *   Go to `Java Build Path` > `Libraries` tab.
    *   Click `Add External JARs...`.
    *   Navigate to where you downloaded your `mysql-connector-java-X.X.X.jar` file, select it, and click `Open`.
    *   Click `Apply and Close`.

6.  **Run the Application:**
    *   Right-click on `Banking.java` in the "Package Explorer" or in its editor window.
    *   Select `Run As > Java Application`.

7.  **Interact in Console:**
    *   The application will prompt you for sender, receiver, and amount in the Eclipse Console view.

## Project Files

*   `Banking.java`: The main Java class containing all JDBC connection, transaction, and business logic.

## Technologies Used

*   Java
*   JDBC (Java Database Connectivity)
*   MySQL Database

## Contributing

Feel free to suggest improvements or enhancements, such as adding more banking operations, better input validation, or a more robust error handling strategy.

## License

This project is open-source and available under the [MIT License](LICENSE).
