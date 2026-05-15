# Rewards Program API

This Spring Boot service calculates reward points for customers based on transaction activity.

## Key Concepts

- 2 points for every dollar spent over $100
- 1 point for every dollar spent over $50 up to $100
- 0 points for the first $50

Example: `120 = 90 points`

## Project Structure

- `com.example.rewardsprogram.model` — domain entities for `Customer` and `Transaction`
- `com.example.rewardsprogram.repository` — Spring Data JPA repositories
- `com.example.rewardsprogram.service` — business logic for reward calculations
- `com.example.rewardsprogram.controller` — REST API endpoints
- `com.example.rewardsprogram.dto` — response and error payload models
- `com.example.rewardsprogram.bootstrap` — application startup data loader

## Run the application

```bash
git clone <repo-url>
cd rewards-program-api
mvn clean spring-boot:run
```

The application starts on `http://localhost:8080`.

## API Endpoint

```http
GET /rewards/{customerId}?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd
```

### Request parameters

- `customerId` — numeric customer identifier
- `startDate` — inclusive start date in `YYYY-MM-DD`
- `endDate` — inclusive end date in `YYYY-MM-DD`

### Sample request

```http
GET http://localhost:8080/rewards/1?startDate=2023-01-01&endDate=2023-03-31
```

### Sample response

```json
{
  "customerId": 1,
  "monthlyPoints": {
    "2023-01": 40,
    "2023-02": 30
  },
  "totalPoints": 70
}

## Error handling

Invalid dates or date ranges return a `400 Bad Request` with a standardized JSON error payload.

## Tests

Run unit and controller tests with:

```bash
mvn test
```

