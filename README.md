# Axon Spring Boot

Welcome to the Axon Spring Boot project! This application allows you to manage accounts using the CQRS (Command Query Responsibility Segregation) and Event Sourcing patterns with the AXON Framework and Spring Boot.

## Prerequisites

Before you begin, make sure you have the following prerequisites installed on your system:

- Java 8 or higher
- Maven
- Git

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/ra-ens/axon-spring-boot.git
```

2. Navigate to the project directory:

```bash
cd axon-spring-boot
```

3. Build the project with Maven:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

The application will start running on port 8083.

## Project Structure
The project is structured as follows:

- **com.abdelhakimrafik.accountService**: contains the main application class
- **com.abdelhakimrafik.accountService.commonapi**: contains common apis for both commands and queries
- **com.abdelhakimrafik.accountService.queries**: contains the query model and repository for the account view
- **com.abdelhakimrafik.accountService.commands**: contains the command handlers for the account Aggregate

## Using the Application
Once the application is running, you can use the following endpoints to manage accounts:

- `GET /query/accounts`: get list of all accounts
- `GET /query/accounts/{accountId}`: retrieves account details by id
- `GET /query/accounts/transactions/{accountId}`: get accounts transactions by account id

## License
This project is licensed under the MIT License.
