# Rewards Program API

Spring Boot API that calculates customer reward points from transaction data.

## Reward Rules

- 2 points for every dollar spent over $100
- 1 point for every dollar spent over $50 up to $100
- 0 points for the first $50

Example: `$120 = 90 points`


API base URL:

http://localhost:8080

## Endpoint

GET /rewards/{customerId}?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd

Example:

GET URL : "http://localhost:8080/rewards/1?startDate=2023-01-01&endDate=2023-03-31"
