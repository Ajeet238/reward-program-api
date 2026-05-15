# Rewards Program API

A Spring Boot REST service that calculates monthly and total reward points per customer from transaction history.

## Reward Rules

- `2 points` per dollar spent over `$100`
- `1 point` per dollar spent between `$50` and `$100`
- `0 points` for the first `$50`

Example: A `$120` purchase earns `90 points`.

## Seeded Customer Data

This application starts with a simple sample dataset loaded at startup.

| Customer ID | Name       | Notes |
|-------------|------------|-------|
| `1`         | John Doe   | Multiple transactions across Jan, Feb, Mar 2023 |
| `2`         | Jane Smith | Smaller monthly purchases across Jan, Feb, Mar 2023 |

### Sample Transactions

- Customer `1` - John Doe
  - `2023-01-15`: `$120`
  - `2023-01-20`: `$80`
  - `2023-02-10`: `$150`
  - `2023-02-25`: `$60`
  - `2023-03-05`: `$200`
- Customer `2` - Jane Smith
  - `2023-01-10`: `$90`
  - `2023-02-15`: `$110`
  - `2023-03-20`: `$70`

## Run the application

```bash
git clone <repo-url>
cd rewards-program-api
mvn clean spring-boot:run
```

The service starts on `http://localhost:8080`.

## API Endpoint

```http
GET /rewards/{customerId}?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd
```

### Request parameters

- `customerId` — numeric customer identifier
- `startDate` — inclusive start date in `YYYY-MM-DD`
- `endDate` — inclusive end date in `YYYY-MM-DD`

### Example request

```http
GET http://localhost:8080/rewards/1?startDate=2023-01-01&endDate=2023-03-31
```

### Example response

```json
{
  "customerId": 1,
  "monthlyPoints": {
    "2023-01": 120,
    "2023-02": 160,
    "2023-03": 250
  },
  "totalPoints": 530
}
```

## Swagger / OpenAPI Documentation

After starting the app, access interactive API docs at:

- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/swagger-ui/index.html`

Raw OpenAPI JSON is available at:

- `http://localhost:8080/v3/api-docs`

## Error handling

- Invalid date formats or date ranges return `400 Bad Request`.
- Missing customers return `404 Not Found` with a message like `Customer with id {id} not found`.

## Project structure

- `src/main/java/com/example/rewardsprogram/model` — domain entities
- `src/main/java/com/example/rewardsprogram/repository` — Spring Data JPA repositories
- `src/main/java/com/example/rewardsprogram/service` — rewards business logic
- `src/main/java/com/example/rewardsprogram/controller` — REST endpoints and exception handling
- `src/main/java/com/example/rewardsprogram/dto` — response and error payloads
- `src/main/java/com/example/rewardsprogram/bootstrap` — sample data loader
- `src/test/java/com/example/rewardsprogram` — unit and controller tests

## Tests

Run the test suite with:

```bash
mvn test
```

