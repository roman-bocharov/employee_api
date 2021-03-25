# Employees Management System
It's a simple Restful API to create employees and update their state.

## How to run the application
```./mvnw spring-boot:run```

## How to create an employee
Just send an HTTP request via any tool you prefer e.g. curl.
curl -X POST http://localhost/employee -H "Content-Type: application/json" -d "{\"firstName\":\"Peter\",\"lastName\":\"Parker\",\"age\":\"28\"}"
     
## How to update an employee's state
Just send an HTTP request via any tool you prefer e.g. curl.
curl -X PUT http://localhost/employee/1 -H "Content-Type: application/json" -d "{\"state\":\"APPROVED\"}"

## How to run the tests
```./mvnw clean test```