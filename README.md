# CP Kirana Store

The Kirana Store Backend is designed to manage the transactions of a small grocery store. It provides RESTful APIs for recording transactions, generating financial reports (weekly, monthly, yearly), handling user authentication and authorization, and rate-limiting API requests.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Documentation](#api-documentation)
- [Security](#security)
- [How It Works](#how-it-works)


## Features

- **User Authentication & Authorization:**
    - Role-based access control (`READ_WRITE` and `READ_ONLY` roles).
    - Basic authentication with HTTP Basic Auth.
- **Transactions Management:**
    - Record, get, update, and delete transactions.
    - Transactions are automatically converted to "INR" if entered in a different currency using an external currency conversion service.
- **Reporting:**
    - Generate financial reports (`weekly`, `monthly`, `yearly`).
- **API Rate Limiting:**
    - Prevents abuse by limiting the number of requests (10 requests per minute).
- **Currency Conversion:**
    - Converts transactions from various currencies to `INR` before saving.

## Project Structure

```
kirana/
├── .mvn/wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── kirana/
│   │   │               ├── utils/
│   │   │               ├── controller/
│   │   │               ├── dto/ 
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │                     └── implementation/
│   │   │               
│   │   └── resources/
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── kirana/
└── target/
```

## Technologies Used

- Programming Language: Java 21
- Framework: Spring Boot 3.3.4
- Dependency Management: Maven
- Build Tools: Maven
- Security: Spring Security (Basic Authentication)
- Database: MongoDB
- Lombok : For reducing boilerplate code
- API Rate Limiting: Bucket4j




## Setup and Installation

- Prerequisites
       - Java 21
       - Maven
       - MongoDB
       - Postman for testing APIs

Steps to Run the Project:

 1. Clone the repository:
   ```
   git clone https://github.com/yourusername/kirana-store-backend.git

   ```
 2. Navigate to the project directory:
   ```
   cd kirana-store-backend
   ```
   
 3. Build the project: For Maven:
   ```
   ./mvnw clean install

   ```
 4. Run the application:
   ```
   ./mvnw spring-boot:run

   ```
 5. MongoDB Configuration:
      By default, the application connects to a MongoDB instance running on `localhost:27017`. Ensure that MongoDB is running and configured properly.
      (see `application.properties`)

Access the API: The APIs can be accessed at `http://localhost:8080`.

# API Documentation

## Base URL

The base URL for all API requests is:
`http://localhost:8080/api`


## Authentication

All API requests require Basic Authentication. Use the following credentials:

- **Owner**: 
  - Username: `owner`
  - Password: `owner123`
  
- **User**: 
  - Username: `user`
  - Password: `user123`

## Rate Limiting

- Limit of 10 requests per minute per user.
- A `429 Too Many Requests` status will be returned if the limit is exceeded with the message: 
  - "You are sending too many requests, wait for some time!"
 
    


## Endpoints

### Transactions API

#### Create Transaction

- **Endpoint:** `POST /transactions`
- **Description:** Record a new transaction (converted to INR if necessary).
- **Request Body:**

    ```json
    {
        "userId": "3",
        "type": "DEBIT",
        "currency": "USD",
        "amount": 200.0,
        "transactionDate": "2024-11-22T09:18:35"
    }
    ```

- **Response:**

    - **Success (201 Created):**
    
    ```json
    {
      "id": "60dba0fdfcd4b72e989ccf9f",
      "amount": 7500.00,
      "currency": "INR",
      "date": "2024-11-22T09:18:35"
    }
    ```

- **Authorization:** Requires `READ_WRITE` role.

---

#### Get Transactions by User

- **Endpoint:** `GET /transactions/user/{userId}`
- **Description:** Get all transactions for a specific user.
- **Response:**

    - **Success (200 OK):**
    
    ```json
    [
      {
        "id": "60dba0fdfcd4b72e989ccf9f",
        "amount": 7500.00,
        "currency": "INR",
        "date": "2024-09-27T12:00:00Z"
      }
    ]
    ```

- **Authorization:** Requires `READ_WRITE` or `READ_ONLY` role.
  
![UnAuthorized](https://github.com/Earlymoon/MyProjectImages/blob/master/first.png?raw=true)

![Authorized](https://github.com/user-attachments/assets/bbf8a392-2e4b-456b-80a7-af212123d470)



---

#### Update Transaction

- **Endpoint:** `PUT /transactions/user/{id}`
- **Description:** Update an existing transaction.
- **Request Body:**

    ```json
    {
         "userId": "3",
        "type": "DEBIT",
        "currency": "USD",
        "amount": 500.0,  //amount 400->500
        "transactionDate": "2024-11-22T09:18:35"
      
    }
    ```

- **Response:**

    - **Success (200 OK):**
    
    ```json
    {
        "id": "60dba0fdfcd4b72e989ccf9f",
        "userId": "3",
        "type": "DEBIT",
        "currency": "USD",
        "amount": 500.0,
        "transactionDate": "2024-11-22T09:18:35"
    }
    ```

    - **Error (404 Not Found):**
    
    ```json
    {
      "error": "Transaction not found"
    }
    ```

- **Authorization:** Requires `READ_WRITE` role.

---

#### Delete Transaction

- **Endpoint:** `DELETE /transactions/{id}`
- **Description:** Delete a transaction by its ID.
- **Response:**

    - **Success (204 No Content):**
    
    - **Error (404 Not Found):**
    
    ```json
    {
      "error": "Transaction not found"
    }
    ```

- **Authorization:** Requires `READ_WRITE` role.

---

### Reporting API

#### Get Weekly Report

- **Endpoint:** `GET /reporting/weekly`
- **Description:** Generate a weekly financial report.
- **Response:**

    - **Success (200 OK):**
    
    ```json
    {
      "totalCredits": 5000.00,
      "totalDebits": 2000.00,
      "netFlow" : 3000.00
    }
    ```

    ![Weekly Report](https://github.com/user-attachments/assets/9a565574-046e-4c8b-a739-01b0917e4a06)


- **Authorization:** Requires `READ_WRITE` or `READ_ONLY` role.

---

#### Get Monthly Report

- **Endpoint:** `GET /reporting/monthly`
- **Description:** Generate a monthly financial report.
- **Response:**

    - **Success (200 OK):**
    
    ```json
    {
      "totalCredits": 20000.00,
      "totalDebits": 8000.00,
      "nerFlow" : 12000.00
    }
    ```

- **Authorization:** Requires `READ_WRITE` or `READ_ONLY` role.

---

#### Get Yearly Report

- **Endpoint:** `GET /reporting/yearly`
- **Description:** Generate a yearly financial report.
- **Response:**

    - **Success (200 OK):**
    
    ```json
    {
      "totalCredits": 250000.00,
      "totalDebits": 100000.00,
      "netFlow" : 15000.00
    }
    ```

- **Authorization:** Requires `READ_WRITE` or `READ_ONLY` role.

---

## Error Handling

Common error responses:

- **400 Bad Request:**
    ```json
    {
      "error": "Description of the error"
    }
    ```

- **401 Unauthorized:**
    ```json
    {
      "error": "Unauthorized access"
    }
    ```

- **404 Not Found:**
    ```json
    {
      "error": "Resource not found"
    }
    ```

- **429 Too Many Requests:**
    ```json
    {
      "error": "You are sending too many requests, wait for some time!"
    }
    ```

  ![Rate Limit](https://github.com/user-attachments/assets/dd349e16-f6a7-499c-a4b9-c8e3d2f7e999)


---

# How It Works

## Transaction Recording

1. **Creating a Transaction:**
   - A transaction is created through the `POST /api/transactions` endpoint. The request must include the transaction details, such as amount, currency, and description.
  
2. **Currency Conversion:**
   - If the currency is not INR, the application automatically converts the transaction amount to INR using an external currency conversion service before saving it to the database.

3. **Saving to Database:**
   - The transaction details, including the converted amount, currency, description, and date, are saved in the MongoDB database.

---

## Generating Reports

1. **Weekly, Monthly, and Yearly Reports:**
   - Reports can be generated by accessing the respective endpoints: 
     - `GET /api/reporting/weekly`
     - `GET /api/reporting/monthly`
     - `GET /api/reporting/yearly`

2. **Data Aggregation:**
   - The application aggregates transaction data based on the date range specified (weekly, monthly, yearly) and calculates total credits, total debits, and net flow.

3. **Response Format:**
   - The generated report is returned in JSON format, providing a clear summary of financial performance over the specified period.

---

## Rate Limiting

1. **Request Limits:**
   - Each user is allowed a maximum of 10 requests per minute. This helps prevent abuse and ensures fair usage of the API.

2. **Handling Rate Limit Exceeded:**
   - If a user exceeds the rate limit, the application responds with a `429 Too Many Requests` status, indicating that the user should wait before making additional requests.

---

## Security Features

1. **Authentication:**
   - Basic HTTP Authentication is used to secure the API. Users must provide valid credentials (username and password) for access.

2. **Role-Based Access Control:**
   - The application implements role-based access control to restrict access to specific endpoints based on user roles (READ_WRITE and READ_ONLY).

3. **Data Security:**
   - Sensitive data, including user credentials, is handled securely to prevent unauthorized access.

---


# Security

## Authentication

- The application employs **Basic HTTP Authentication** to secure the API endpoints. Users must provide valid credentials in the format:
  - **Username:** `owner` 
  - **Password:** `owner123` (has **READ_WRITE** role)
  
  - **Username:** `user` 
  - **Password:** `user123` (has **READ_ONLY** role)

## Authorization

- The application implements **role-based access control** to restrict access to specific endpoints based on user roles:
  - **Transaction Endpoints (`/api/transactions/**`):** 
    - Requires **READ_WRITE** role for creating, updating, and deleting transactions.
  
  - **Reporting Endpoints (`/api/reporting/**`):**
    - Requires either **READ_WRITE** or **READ_ONLY** role for accessing financial reports.

## Rate Limiting

- The API enforces rate limiting to prevent abuse by limiting the number of requests:
  - **Limit:** 10 requests per minute per user.
  - If the limit is exceeded, the application responds with a **429 Too Many Requests** status and the message:
    - `"You are sending too many requests, wait for some time!"`

## Data Security

- Sensitive information, such as user credentials and transaction data, is handled securely.
- Passwords are not stored in plaintext; best practices for password storage and security should be followed.
- The application is designed to prevent common security vulnerabilities such as Cross-Site Scripting (XSS), and Cross-Site Request Forgery (CSRF).

## Conclusion

The security features of the Kirana Store Backend ensure that user data is protected and access to sensitive operations is restricted based on user roles. The combination of authentication, authorization, and rate limiting helps maintain a secure environment for transaction management.



