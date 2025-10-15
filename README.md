# 🍃 Spring Boot MongoDB CRUD REST API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-green.svg)](https://www.mongodb.com/)
[![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue.svg)](https://maven.apache.org/)

A comprehensive Spring Boot REST API implementation demonstrating CRUD operations with MongoDB using MongoTemplate. This project serves as a practical tutorial for building robust REST APIs with Spring Boot and MongoDB.

## 📺 Tutorial

This repository is the source code for the YouTube tutorial: **[Spring Boot REST API From Scratch with MongoDB & MongoTemplate](https://www.youtube.com/watch?v=8m0kdCBj9HU)**

## ✨ Features

- 🔧 **Complete CRUD Operations**: Create, Read, Update, and Delete users
- ✅ **Data Validation**: Bean validation with custom error messages
- 🔍 **Unique Email Constraint**: Prevents duplicate email registrations
- 🏗️ **Clean Architecture**: Service layer pattern implementation
- 📊 **MongoDB Integration**: Uses MongoTemplate for flexible database operations
- 🚀 **RESTful API Design**: Follows REST principles with proper HTTP status codes
- 📝 **Comprehensive Documentation**: Well-documented code with clear examples

## 🛠️ Technology Stack

- **Java 21** - Programming language
- **Spring Boot 3.5.6** - Framework
- **Spring Data MongoDB** - Database integration
- **Spring Boot Validation** - Data validation
- **MongoDB** - NoSQL database
- **Maven** - Dependency management
- **MongoTemplate** - MongoDB operations

## 📋 Prerequisites

Before running this application, make sure you have:

- ☕ **Java 21** or later installed
- 🍃 **MongoDB** running on `localhost:27017` (local installation) **OR** 🐳 **Docker** (for containerized MongoDB)
- 🔨 **Maven 3.6+** for building the project
- 🖥️ **IDE** like IntelliJ IDEA, Eclipse, or VS Code

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/learnwithiftekhar/spring-mongo-templete-crud-rest-api.git
cd spring-mongo-templete-crud-rest-api
```

### 2. Start MongoDB

You have two options to run MongoDB:

#### Option A: Local MongoDB Installation

Make sure MongoDB is running on your local machine:

```bash
# On macOS with Homebrew
brew services start mongodb-community

# On Ubuntu/Debian
sudo systemctl start mongod

# On Windows
net start MongoDB
```

#### Option B: Using Docker (Recommended) 🐳

If you prefer to run MongoDB in a Docker container:

```bash
# Pull and run MongoDB container
docker run --name mongodb \
  -p 27017:27017 \
  -d mongo:latest

# Or with persistent data volume
docker run --name mongodb \
  -p 27017:27017 \
  -v mongodb_data:/data/db \
  -d mongo:latest

# To stop the container
docker stop mongodb

# To start existing container
docker start mongodb

# To remove the container (optional)
docker rm mongodb
```

**Note**: The Docker option requires Docker to be installed on your system.

### 3. Build and Run the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### User Management

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `POST` | `/api/users` | Create a new user | User JSON |
| `GET` | `/api/users` | Get all users | - |
| `GET` | `/api/users/{id}` | Get user by ID | - |
| `PUT` | `/api/users/{id}` | Update user | User JSON |
| `DELETE` | `/api/users/{id}` | Delete user | - |

### User Model

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 25,
  "city": "New York"
}
```

### Field Validations

- **name**: Required, cannot be blank
- **email**: Required, must be valid email format, unique
- **age**: Must be between 18 and 100
- **city**: Optional

## 🔧 Usage Examples

### Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 25,
    "city": "New York"
  }'
```

### Get All Users

```bash
curl -X GET http://localhost:8080/api/users
```

### Get User by ID

```bash
curl -X GET http://localhost:8080/api/users/{userId}
```

### Update a User

```bash
curl -X PUT http://localhost:8080/api/users/{userId} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "age": 30,
    "city": "Los Angeles"
  }'
```

### Delete a User

```bash
curl -X DELETE http://localhost:8080/api/users/{userId}
```

## ⚙️ Configuration

The application uses MongoDB configuration with environment variable support in `application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: ${MONGODB_URI:mongodb://localhost:27017/crud_tutorial}
      database: ${MONGODB_DATABASE:crud_tutorial}
```

### Environment Variables

You can customize the MongoDB connection by setting these environment variables:

- **`MONGODB_URI`**: Complete MongoDB connection string (default: `mongodb://localhost:27017/crud_tutorial`)
- **`MONGODB_DATABASE`**: Database name (default: `crud_tutorial`)

#### Examples:

**Using environment variables:**
```bash
# Set custom MongoDB URI and database
export MONGODB_URI=mongodb://your-server:27017/your_database
export MONGODB_DATABASE=your_database

# Run the application
mvn spring-boot:run
```

**Using Docker with environment variables:**
```bash
# Run with custom MongoDB settings
mvn spring-boot:run -Dspring-boot.run.arguments="--MONGODB_URI=mongodb://mongodb-server:27017/mydb --MONGODB_DATABASE=mydb"
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/learnwithiftekhar/mongo_crud_api/
│   │   ├── MongoCrudApiApplication.java       # Main application class
│   │   ├── controller/
│   │   │   └── UserController.java            # REST controller
│   │   ├── model/
│   │   │   └── User.java                      # User entity
│   │   └── service/
│   │       └── UserService.java               # Business logic
│   └── resources/
│       └── application.yml                    # Configuration
└── test/
    └── java/com/learnwithiftekhar/mongo_crud_api/
        └── MongoCrudApiApplicationTests.java  # Tests
```

## 🎯 Key Learning Points

- 📚 **MongoTemplate Usage**: Direct MongoDB operations without JPA
- 🔒 **Data Validation**: Using Bean Validation annotations
- 🏛️ **Service Layer Pattern**: Separation of concerns
- 🌐 **REST API Design**: Proper HTTP methods and status codes
- 🔧 **Spring Boot Configuration**: YAML-based configuration
- 📊 **MongoDB Integration**: Document-based database operations

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Contact

**Md. Iftekhar Hossain**

- 📧 **Email**: [learnwithiftekhar@gmail.com](mailto:learnwithiftekhar@gmail.com)
- 💼 **LinkedIn**: [www.linkedin.com/in/hossain-md-iftekhar](https://www.linkedin.com/in/hossain-md-iftekhar)
- 📺 **YouTube**: [Spring Boot REST API From Scratch with MongoDB & MongoTemplate](https://www.youtube.com/watch?v=8m0kdCBj9HU)

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

⭐ **Star this repository if you found it helpful!**