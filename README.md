# Budgeting - A Spring Boot GraphQL Server

Modern GraphQL server implementation using Spring Boot, providing a flexible and efficient API layer for a budgeting application.

## 📋 Prerequisites

- Java 21+
- Gradle
- IntelliJ IDEA

## 🛠️ Tech Stack

- Spring Boot 3.4.1
- Spring GraphQL
- Spring Data JPA
- H2 Database (With offloaded dummy data)
- Gradle 8.11.1

## 🚀 Running the Server

1. Start the server: ./gradlew clean bootRun
2. Access GraphiQL interface: http://localhost:8080/graphiql
3. Playaround

## 📚 Playaround examples

```json
query {
  owners {
    name
    transactions {
      cashFlow
      amount
    }
    savingsGoals {
      targetAmount
      savedAmount
    }
    budgets {
      category
      spent
      threshold
    }
  }
 ownerById(id: 1) {
    currency
    name
    	budgets {
      	category
     	  spent
      	threshold
    }
  }
}
```
```json
mutation {
  addTransaction(transaction: { cashFlow: INCOME, initiatedOn: "2024-12-20T11:33:20Z", amount: 2950, ownerId: 1, description:"Test" }) {
    cashFlow
    id
    amount
  }
  addTransaction(transaction: { cashFlow: EXPENSE, category:"Food", initiatedOn: "2024-12-20T11:33:20Z", amount: 100, description:"Test" }) {
    cashFlow
    id
    amount
  }
  addOwner(owner: { name:"Dummy", email:"dummy@gmail.com", currency:EUR }) {
    name
    id
    email
    currency
  }
}
```
```json
subscription {
  budgetPublished(ownerId: 1) {
    category
    spent
    threshold
    transactions {
      id
      amount
    }
  }
}
```

Edit: For addTransaction after adding security, the following header should be present.
```json
{
  "Authorization": "Bearer TOKEN",
  "user_id": "1",
  "user_roles":"add:transaction"
}
```
