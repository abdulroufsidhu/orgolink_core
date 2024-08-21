# Ambaar - Spring Boot Point of Sales System

Ambaar is an open-source Point of Sales (POS) system built using Spring Boot, PostgreSQL, and HTMX.

## Features
   - Super fast with the use of [Redis](https://redis.io/)
   - Auth
   - Multi Business(es)
   - Multi Branch(es)
   - Isolated Inventory (per branch)
   - Isolated Sales (per branch)
   - Multi Employments
   - Record Backtracking (Which user generated specific record)

## Requirements

- [Docker Compose](https://docs.docker.com/compose/install/)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/abdulroufsidhu/ambar_springboot.git
   cd ambar_springboot
   ```

1. **Build and run the project:**

   ```bash
   ./gradlew clean build
   docker compose up
   ```

1. **Access Swagger Api Documentation:**

   Open your browse and [go to](http://localhost:8080/docs/swagger-ui/index.html) `http://localhost:8080/docs/swagger-ui/index.html`

1. **Access the application:**

   Open your browser and [go to](http://localhost:8080) `http://localhost:8080` to access the application.

## Dependencies

- [Java 21](https://www.java.com/en/)
- [PostgreSQL (latest LTS version)](https://www.postgresql.org/)
- [Redis V6.2](https://redis.io/)

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
