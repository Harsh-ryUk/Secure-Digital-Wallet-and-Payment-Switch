# Deployment Guide

## Prerequisites
- [Docker](https://www.docker.com/get-started) installed and running.
- [Java 17](https://adoptium.net/) (optional, if running locally without Docker).

## 🐳 Docker Deployment (Recommended)

This is the easiest way to run the service along with its database dependency.

1.  **Build the JAR file**:
    ```bash
    ./mvnw.cmd clean package -DskipTests
    ```

2.  **Start the services**:
    ```bash
    docker-compose up --build
    ```

    This will:
    - Start a MySQL container.
    - Build the Account Service image.
    - Start the Account Service container connected to MySQL.

3.  **Access the Service**:
    The service will be available at `http://localhost:8081`.

4.  **Stop the services**:
    ```bash
    docker-compose down
    ```

## ☕ Local Deployment

If you prefer to run the service directly on your machine:

1.  **Ensure MySQL is running**:
    Make sure you have a MySQL instance running on `localhost:3306` with a database named `account_service` and credentials `root/root`.

2.  **Run the application**:
    ```bash
    ./mvnw.cmd spring-boot:run
    ```
