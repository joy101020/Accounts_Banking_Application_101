# Account Microservice

## Overview
Account Microservice is a Spring Boot REST API for managing bank accounts.
It uses OpenFeign to communicate with the Customer Microservice and validate customers before creating accounts.

### Features

* Create Account
* Get Account By ID
* Get All Accounts
* Get Accounts by customer Id
* Debit account
* Credit account
* Get Balance 
* Delete Account
* Feign Client Communication

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* OpenFeign
* Maven

---

## API Endpoints

```http
POST   /account/add/{customerId}
GET    /account/customer/{customerId}
GET    /account/{accountId}
GET    /account/all
GET    /account/{accountId}
PUT    /account/debit/{accountId}
PUT    /account/credit/{accoutnId}
GET    /account/balance/{accountId}
DELETE /account/delete/{accountId}
```

---

## Feign Client

```java
@FeignClient(name = "customer", url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping("/customer/{customerId}")
    Customer getCustomer(@PathVariable("customerId") Long customerId);
}
```

---

## Database Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/account
spring.jpa.hibernate.ddl-auto=update
```

---

## Run Application

```bash
git clone https://github.com/joy101020/Account.git
cd Account
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8082
```

---

## Microservice Flow

```text
Account Service ---> Customer Service
        |                |
        |---Feign------->|
```
