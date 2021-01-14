# adyen-payment-integration-service

#### Swagger
Documentation is available at http://localhost:15010/openapi-ui.html

PoC (Proof of Concept) project for representing implementation of 
Web Components Integration Guide from Adyen payment provider.

#### Documentation: https://docs.adyen.com/
#### Payment Fundamentals: https://docs.adyen.com/payments-fundamentals/payments-lifecycle
#### Adyen API Explorer: https://docs.adyen.com/api-explorer/
#### Web Components integration guide: https://docs.adyen.com/checkout/components-web
#### Adyen Java API: https://github.com/Adyen/adyen-java-api-library
#### CC Test Data: https://docs.adyen.com/development-resources/test-cards/test-card-numbers#visa
#### CC Test Failed Cases Data: https://docs.adyen.com/development-resources/test-cards/result-code-testing/adyen-response-codes
#### SEPA Test Data: https://docs.adyen.com/payment-methods/sepa-direct-debit/api-only

#### Used technologies:

- Spring Boot
- Feign as declarative REST client
- Consul for Service Discovery
- Hystrix as a latency and fault tolerance library
- Ribbon for client side load balancing **(?)**
- Flyway as Database migration tool
- PostgreSQL as RDBMS

#### 3D Secure Request Example:
    {
      "paymentPayload": "{\"payloadType\": \"ORDER_PAYMENT\", \"paymentPurpose\": \"ORDER_DOWNPAYMENT\", \"orderId\": \"a62c111b-a92d-45ec-a2fd-ec635425ebc7\"}",
      "currency": "EUR",
      "amountInMinorUnits": 20000,
      "returnUrl": "https://url.com",
      "holderName": "John Smith",
      "encryptedCardNumber": "test_5212 3456 7890 1234",
      "encryptedExpiryMonth": "test_10",
      "encryptedExpiryYear": "test_2020",
      "encryptedSecurityCode": "test_737",
      "adyen3DSecureInfoDTO": {
        "shopperIp": "string",
        "userAgent": "string",
        "acceptHeader": "string",
        "language": "string",
        "colorDepth": 0,
        "screenHeight": 0,
        "screenWidth": 0,
        "timeZoneOffset": 0,
        "javaEnabled": true
      }
    }

#### SEPA Request Example:
    {
      "paymentPayload": "{\"payloadType\": \"ORDER_PAYMENT\", \"paymentPurpose\": \"ORDER_DOWNPAYMENT\", \"orderId\": \"a62c111b-a92d-45ec-a2fd-ec635425ebc7\"}",
      "currency": "EUR",
      "amountInMinorUnits": 40000,
      "ownerName": "A. Klaassen",
      "ibanNumber": "NL13TEST0123456789"
    }