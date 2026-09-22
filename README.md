# Grocery Store Checkout System

A Spring Boot REST API for a grocery store checkout system.
The application calculates the total cost of items in a customer's basket and applies the available special offers.

## Overview

This application provides a checkout service that:
- Accepts a basket of grocery items
- Calculates the subtotal
- Applies applicable special offers
- Calculates the total discount
- Calculates the final total
- Returns an itemized receipt

## Supported Items and Prices

| Item    | Price |
|---------|-------|
| Bananas | £0.50 each |
| Oranges | £0.30 each |
| Apples  | £0.60 each |
| Lemons  | £0.25 each |
| Peaches | £0.75 each |

## Special Offers

The application supports the following promotions:
- **Bananas:** Buy 2, get 1 free
- **Oranges:** 3 for £0.75

## Technology Stack
- Java 17
- Spring Boot
- Spring Web
- Spring Boot Validation
- Maven
- JUnit 5
- Mockito
- Lombok

## Project Structure
| Package | Responsibility |
|---|---|
| `controller` | Handles REST API requests |
| `service` | Contains checkout and pricing business logic |
| `discount` | Contains discount strategies and promotion rules |
| `model` | Contains request, response, and domain models |
| `exception` | Handles application exceptions and error responses |
| `grocery_checkout_service` | Contains the Spring Boot application entry point |

### Main Classes
| Class | Purpose |
|---|---|
| `CheckoutController' | Exposes the `/checkout` REST API |
| `CheckoutService` | Calculates subtotal, discounts, and final total |
| `PriceCatalog` | Stores product prices |
| `DiscountStrategy` | Defines the discount strategy interface |
| `BananaDiscountStrategy` | Implements the Banana promotion |
| `OrangeDiscountStrategy` | Implements the Orange promotion |
| `GlobalExceptionHandler` | Handles validation and application exceptions |
| `InvalidCheckoutException` | Represents invalid checkout conditions |
| `Receipt` | Represents the checkout result |


## Setup
1. Clone the repository:
    git clone <https://github.com/alok0506/grocery-checkout.git>
2. Open the project in IDE.
3. Run the application using:
   ./mvnw spring-boot:run
4. The application will start at:
   http://localhost:8080
5. Run Tests
    ./mvnw clean test


## API
**Method:** POST
**URL:**
    http://localhost:8080/checkout/receipt
## RequestBody
{
"items": [
    {
    "itemType": "BANANA",
    "quantity": 3
    },
    {
    "itemType": "ORANGE",
    "quantity": 4
    },
    {
    "itemType": "APPLE",
    "quantity": 1
    }
        ]
}

## Response
{
    "items": [
        {
        "itemType": "BANANA",
        "quantity": 3,
        "unitPrice": 0.50,
        "lineTotal": 1.50
        },
        {
        "itemType": "ORANGE",
        "quantity": 4,
        "unitPrice": 0.30,
        "lineTotal": 1.20
        },
        {
        "itemType": "APPLE",
        "quantity": 1,
        "unitPrice": 0.60,
        "lineTotal": 0.60
        }
            ],
    "subTotal": 3.30,
    "discounts": [
        {
        "description": "Buy 2 Get 1 Free - Bananas",
        "amount": 0.50
        },
        {
        "description": "3 Oranges for 0.75",
        "amount": 0.15
        }
                ],
    "totalDiscount": 0.65,
    "total": 2.65
}