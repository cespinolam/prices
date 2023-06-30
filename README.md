# Prices Application

This is a Spring Boot application that provides an API for retrieving prices based on parameters such as date, product ID, and brand ID.

## Getting Started

These instructions will help you set up and run the project on your local machine for development and testing purposes.

### Prerequisites

To run this application, you need to have the following software installed on your machine:

- Java Development Kit (JDK) 8 or later
- Maven

### Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/cespinolam/prices.git
   ```

2. Navigate to the project directory:

   ```bash
   cd prices-application
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

### Usage

1. Start the application:

   ```bash
   mvn spring-boot:run
   ```

2. The application will be accessible at: `http://localhost:8080/api/prices`

3. Use a tool like Postman to send HTTP requests to the API endpoints.

### API Endpoints

- **GET /api/prices** - Retrieves the price based on the specified parameters.

   Query Parameters:
   - `date` (required) - The date in ISO format (yyyy-MM-dd).
   - `productId` (required) - The ID of the product.
   - `brandId` (required) - The ID of the brand.

   Example Request:
   ```
   GET /api/prices?date=2023-06-29&productId=123&brandId=456
   ```

   Example Response:
   ```json
   {
       "id": 1,
       "productId": 123,
       "brandId": 456,
       "price": 19.99,
       "currency": "USD",
       "startDate": "2023-06-29",
       "endDate": "2023-07-05"
   }
   ```

### Running Tests

You can run the tests using the following command:

```bash
mvn test
```

## Built With

- Spring Boot - Java framework for creating web applications.
- Maven - Dependency management and build tool.
- H2 Database - In-memory database for development and testing.

## Contributing

## License

## Acknowledgments
