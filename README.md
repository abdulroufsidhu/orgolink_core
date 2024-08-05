# Ambaar - Spring Boot Point of Sales System

Ambaar is an open-source Point of Sales (POS) system built using Spring Boot, PostgreSQL, and HTMX.

## Requirements

- Java 17
- PostgreSQL (latest LTS version)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/abdulroufsidhu/ambar_springboot.git
   cd ambar_springboot
   ```

2. **Database Configuration:**

   You have two options for configuring the database:

   - **Option 1:** Create a PostgreSQL user and database with the following credentials:
     - Username: `abdul`
     - Password: `abdul`
     - Database: `abdul`

     You can create the user and database using the following SQL commands:

     ```sql
     CREATE USER abdul WITH PASSWORD 'abdul';
     CREATE DATABASE abdul;
     GRANT ALL PRIVILEGES ON DATABASE abdul TO abdul;
     ```

   - **Option 2:** Edit the `application.properties` file with your own PostgreSQL credentials and database name. The `application.properties` file is located in `src/main/resources/application.properties`. Update the file with your own settings:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and run the project:**

   ```bash
   ./gradlew build
   ./gradlew bootRun
   ```

4. **Access the application:**

   Open your browser and go to `http://localhost:8080` to access the application.

## Dependencies

- Java 17
- PostgreSQL (latest LTS version)

## Project Structure

- **src/main/java**: Contains the Java source code.
- **src/main/resources**: Contains the application configuration files.
- **build.gradle**: The Gradle build file.

## Contributing

Feel free to submit issues or pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.

## Contact

- **Author**: Abdul Rauf
- **Email**: abdulroufsidhu@hotmail.com
- **LinkedIn**: [Abdul Rauf](https://www.linkedin.com/in/abdulroufsidhu/)
- **GitHub**: [abdulroufsidhu](https://github.com/abdulroufsidhu)
