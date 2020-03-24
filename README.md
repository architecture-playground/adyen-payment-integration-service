# adyen-payment-integration-service

Documentation is available at http://localhost:15010/openapi-ui.html

PoC (Proof of Concept) project for representing implementation of 
Web Components Integration Guide from Adyen payment provider.

### Documentation: https://docs.adyen.com/
### Payment Fundamentals: https://docs.adyen.com/payments-fundamentals/payments-lifecycle
### Adyen API Explorer: https://docs.adyen.com/api-explorer/
### Web Components integration guide: https://docs.adyen.com/checkout/components-web
### Adyen Java API: https://github.com/Adyen/adyen-java-api-library

### Used technologies:

- Spring Boot
- Feign as declarative REST client
- Consul for Service Discovery
- Hystrix as a latency and fault tolerance library
- Ribbon for client side load balancing **(?)**
- Flyway as Database migration tool
- PostgreSQL as RDBMS