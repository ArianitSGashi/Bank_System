# Bank System Spring Boot Restful Web Service

## Overview

This project is a Spring Boot Restful Web Service for a bank system. The system allows users to perform various banking operations such as creating banks and accounts, performing transactions with fees, withdrawing and depositing money, and retrieving account and transaction information. The project also includes proper exception handling and unit/integration tests.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Bank Endpoints](#bank-endpoints)
  - [Account Endpoints](#account-endpoints)
  - [Transaction Endpoints](#transaction-endpoints)
- [Testing](#testing)
  - [Unit/Integration Tests](#unitintegration-tests)
- [Exception Handling](#exception-handling)
- [Troubleshooting](#troubleshooting)

## Features

- Create a bank with all required values
- Create an account
- Perform both flat fee and percent fee transactions from one account to another
- Withdraw and deposit money to an account
- See a list of transactions for any account
- Check account balance for any account
- See a list of bank accounts
- Check bank total transaction fee amount
- Check bank total transfer amount
- Proper exception handling with user-friendly error messages

## Technologies Used

- Java 17
- Spring Boot 3.2.5
- H2 Database
- Spring Data JPA
- Spring Web
- Spring Boot DevTools
- Maven
- Postman

## Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher
- Postman

## Setup Instructions

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/ArianitSGashi/Bank_System.git
    cd bank-system
    ```

    You can run the application from your IDE by running the `BankApplication` main class.

   Alternatively: 

3. **Build the Project:**

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```


## Running the Application

Once the application is running, it will be available at `http://localhost:8080`.
You can use Postman tool: It provides a user-friendly interface for building and sending HTTP requests to an API, inspecting the responses, and organizing these requests in collections for reusability and sharing.

## API Endpoints

### Bank Endpoints

#### 1. Create a Bank

- **Method:** POST
- **URL:** `http://localhost:8080/banks`
- **Body:**

    ```json
    {
      "name": "Test Bank",
      "transactionFlatFeeAmount": 10,
      "transactionPercentFeeValue": 5
    }
    ```

**Steps:**

1. Open Postman and create a new request.
2. Set the request method to POST.
3. Enter the URL `http://localhost:8080/banks`.
4. Go to the Body tab, select raw, and set the type to JSON.
5. Enter the JSON body as shown above.
6. Click Send.
7. Verify that the response contains the created bank with an ID and the correct values.

**Response:**

```json
{
  "id": 1,
  "name": "Test Bank",
  "transactionFlatFeeAmount": 10.0,
  "transactionPercentFeeValue": 5.0,
  "totalTransactionFeeAmount": 0.0,
  "totalTransferAmount": 0.0
}
```

## 2. Get All Banks

- **Method:** GET
- **URL:** `http://localhost:8080/banks`

**Steps:**

1. Create a new request.
2. Set the request method to GET.
3. Enter the URL `http://localhost:8080/banks`.
4. Click Send.
5. Verify that the response contains a list of all banks.

**Response:**

```json
[
  {
    "id": 1,
    "name": "Test Bank",
    "transactionFlatFeeAmount": 10.0,
    "transactionPercentFeeValue": 5.0,
    "totalTransactionFeeAmount": 0.0,
    "totalTransferAmount": 0.0
  }
]
```
## 3. Get a Bank by ID

- **Method:** GET
- **URL:** `http://localhost:8080/banks/{id}`

**Steps:**

1. Create a new request.
2. Set the request method to GET.
3. Enter the URL `http://localhost:8080/banks/{id}`.
4. Click Send.
5. Verify that the response contains the bank with the specified ID.

**Response:**

```json
{
  "id": 1,
  "name": "Test Bank",
  "transactionFlatFeeAmount": 10.0,
  "transactionPercentFeeValue": 5.0,
  "totalTransactionFeeAmount": 0.0,
  "totalTransferAmount": 0.0
}
```
## Account Endpoints

### 4. Create an Account

- **Method:** POST
- **URL:** `http://localhost:8080/accounts`

**Body:**

```json
{
  "name": "Test User",
  "balance": 100.0,
  "bank": {
    "id": 1
  }
}
```

**Steps:**

1. Create a new request.
2. Set the request method to POST.
3. Enter the URL http://localhost:8080/accounts.
4. Go to the Body tab, select raw, and set the type to JSON.
5. Enter the JSON body as shown above.
6. Click Send.
7.Verify that the response contains the created account with an ID and the correct values.

**Response:**

```json
{
  "id": 1,
  "name": "Test User",
  "balance": 100.0,
  "bank": {
    "id": 1,
    "name": "Test Bank",
    "transactionFlatFeeAmount": 10.0,
    "transactionPercentFeeValue": 5.0
  }
}
```
## 5. Get All Accounts

- **Method:** GET
- **URL:** `http://localhost:8080/accounts`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/accounts`.  
4. Click Send.  
5. Verify that the response contains a list of all accounts.

**Response:**

```json
[
  {
    "id": 1,
    "name": "Test User",
    "balance": 100.0,
    "bank": {
      "id": 1,
      "name": "Test Bank",
      "transactionFlatFeeAmount": 10.0,
      "transactionPercentFeeValue": 5.0
    }
  }
]
```
## 6. Get an Account by ID

- **Method:** GET
- **URL:** `http://localhost:8080/accounts/{id}`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/accounts/{id}`.  
4. Click Send.  
5. Verify that the response contains the account with the specified ID.

**Response:**

```json
{
  "id": 1,
  "name": "Test User",
  "balance": 100.0,
  "bank": {
    "id": 1,
    "name": "Test Bank",
    "transactionFlatFeeAmount": 10.0,
    "transactionPercentFeeValue": 5.0
  }
}
```
## 7. Get Accounts by Bank ID

- **Method:** GET
- **URL:** `http://localhost:8080/banks/{id}/accounts`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/banks/{id}/accounts`, replacing `{id}` with the actual bank ID.  
4. Click Send.  
5. Verify that the response contains a list of accounts for the specified bank ID.

**Response:**

```json
[
  {
    "id": 1,
    "name": "Test User",
    "balance": 100.0,
    "bank": {
      "id": 1,
      "name": "Test Bank"
    }
  }
]
```

## Transaction Endpoints

### 8. Perform a Transaction

- **Method:** POST
- **URL:** `http://localhost:8080/transactions`

**Body:**

```json
{
  "amount": 20.0,
  "originatingAccountId": 1,
  "resultingAccountId": 2,
  "reason": "Test transfer"
}
```
**Steps:**

1. Create a new request.
2. Set the request method to POST.
3. Enter the URL http://localhost:8080/transactions.
4. Go to the Body tab, select raw, and set the type to JSON.
5. Enter the JSON body as shown above.
6. Click Send.
7. Verify that the response contains the performed transaction with an ID and the correct values.

**Response:**

```json
{
  "id": 1,
  "amount": 20.0,
  "originatingAccountId": 1,
  "resultingAccountId": 2,
  "reason": "Test transfer",
  "transactionDate": "2024-05-19T00:00:00.000+00:00"
}
```
## 9. Get Transactions for an Account

- **Method:** GET
- **URL:** `http://localhost:8080/transactions/account/{accountId}`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/transactions/account/{accountId}`.  
4. Click Send.  
5. Verify that the response contains a list of transactions for the specified account ID.

**Response:**

```json
[
  {
    "id": 1,
    "amount": 20.0,
    "originatingAccountId": 1,
    "resultingAccountId": 2,
    "reason": "Test transfer",
    "transactionDate": "2024-05-19T00:00:00.000+00:00",
    "accountId": 1
  }
]
```
## 10. Withdraw from Account

- **Method:** POST
- **URL:** `http://localhost:8080/accounts/withdraw`

**Body:**

```json
{
  "accountId": 1,
  "amount": 100
}
```
**Steps:**

1. Create a new request.
2. Set the request method to POST.
3. Enter the URL http://localhost:8080/accounts/withdraw.
4. Go to the Body tab, select raw, and set the type to JSON.
5. Enter the JSON body as shown above.
6. Click Send.
7. Verify that the response contains the updated account balance and details.

**Response:**

```json
{
  "id": 1,
  "name": "Test User",
  "balance": 0.0,
  "bank": {
    "id": 1,
    "name": "Test Bank"
  }
}
```
## 11. Deposit to Account

- **Method:** POST
- **URL:** `http://localhost:8080/accounts/deposit`

**Body:**

```json
{
  "accountId": 1,
  "amount": 200
}
```
**Steps:**

1. Create a new request.
2. Set the request method to POST.
3. Enter the URL http://localhost:8080/accounts/deposit.
4. Go to the Body tab, select raw, and set the type to JSON.
5. Enter the JSON body as shown above.
6. Click Send.
7. Verify that the response contains the updated account balance and details.

**Response:**

```json
{
  "id": 1,
  "name": "Test User",
  "balance": 200.0,
  "bank": {
    "id": 1,
    "name": "Test Bank"
  }
}
```
## 12. Get Bank Total Transaction Fee Amount

- **Method:** GET
- **URL:** `http://localhost:8080/banks/{id}/total-transaction-fee`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/banks/{id}/total-transaction-fee`.  
4. Click Send.  
5. Verify that the response contains the total transaction fee amount for the specified bank.

**Response:**

```json
{
 10.0 //or any other sum, depends on you how you performed transactions.
}
```
## 13. Get Bank Total Transfer Amount

- **Method:** GET
- **URL:** `http://localhost:8080/banks/{id}/total-transfer-amount`

**Steps:**

1. Create a new request.  
2. Set the request method to GET.  
3. Enter the URL `http://localhost:8080/banks/{id}/total-transfer-amount`.  
4. Click Send.  
5. Verify that the response contains the total transfer amount for the specified bank.

**Response:**

```json
{
 300.0 //depends from transactions
}
```

## Testing

To run the tests, use the following command:

```bash
mvn test
```

### Unit/Integration Tests

- **BankServiceTest:** Tests related to creating banks and checking bank statistics.
- **AccountServiceTest:** Tests related to creating accounts, withdrawing and depositing money and checking account balances.
- **TransactionServiceTest:** Tests related to performing transactions and retrieving transaction information.

  ## Exception Handling

The application includes comprehensive exception handling to provide user-friendly error messages for various scenarios, such as:

- **Account Not Found:** Thrown when an account with the specified ID does not exist.
- **Not Enough Funds:** Thrown when an account does not have sufficient funds to perform a transaction.
- **Bank Not Found:** Thrown when a bank with the specified ID does not exist.

- ## Example Exception Response

**Response:**

```json
{
  "timestamp": "2024-05-19T00:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Account not found",
  "path": "/accounts/999"
}
```
## Troubleshooting

**Issue:** `mvn : The term 'mvn' is not recognized as the name of a cmdlet, function, script file, or operable program.`

**Solution:** Ensure that Maven is installed and added to your system's PATH.

**Issue:** `org.hibernate.LazyInitializationException`

**Solution:** Ensure that collections are properly initialized within an active session or use DTOs to avoid lazy initialization issues.


