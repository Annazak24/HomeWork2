# PetStore API Automation

This project contains automated tests for the PetStore Swagger API using Rest-Assured and JUnit 5.

## Tests Implemented
### POST /store/order
- Test 1: Validate successful creation (200 OK)
- Test 2: Validate returned order ID

### GET /store/order/{orderId}
- Test 1: Validate successful retrieval and correct fields
- Test 2: Validate response body against JSON Schema

## Run Tests
```
mvn clean test
```

Run with SpotBugs:
```
mvn clean verify
```

Technologies: Java, Maven, Rest-Assured, JUnit5, Hamcrest, Lombok  
