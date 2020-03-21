# adyen-payment-integration-service

PoC (Proof of Concept) project for testing Adyen payment provider.

### Documentation: https://docs.adyen.com/
### Payment Fundamentals: https://docs.adyen.com/payments-fundamentals/payments-lifecycle
### Adyen API Explorer: https://docs.adyen.com/api-explorer/

### Integration Hints:

#### How to get available payment methods (Collect shopper details):

    Adyan recommends to use dynamically generated payment form: 
    For each transaction, you make a /paymentMethods call in step 1 
    to determine the payment details you need to collect.
    
    Then use the response to generate a form that collects them from the shopper. 
    This takes more time to implement, 
    but ensures that the required payment details you collect from the shopper are up-to-date.
    
    Also you will always have up-to-date available payment methods.

Proofs: https://docs.adyen.com/checkout/api-only?tab=%23codeBlockuKMzI_bash#step-2-collect-shopper-details

### Used technologies:

- Spring Boot
- Feign as declarative REST client
- Consul for Service Discovery
- Hystrix as a latency and fault tolerance library
- Ribbon for client side load balancing **(?)**
- Flyway as Database migration tool
- PostgreSQL as RDBMS