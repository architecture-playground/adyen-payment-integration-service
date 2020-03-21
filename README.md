# adyen-payment-integration-service

PoC (Proof of Concept) project for testing Adyen payment provider.

### Documentation: https://docs.adyen.com/
### Payment Fundamentals: https://docs.adyen.com/payments-fundamentals/payments-lifecycle
### Adyen API Explorer: https://docs.adyen.com/api-explorer/

### Integration Hints:

#### API-only integration guide

Read more here: https://docs.adyen.com/checkout/api-only?tab=%23codeBlockiTPV5_Java#page-introduction

#### How to get available payment methods (Collect shopper details):

    Adyan recommends to use dynamically generated payment form: 
    For each transaction, you make a /paymentMethods call in step 1 
    to determine the payment details you need to collect.
    
    Then use the response to generate a form that collects them from the shopper. 
    This takes more time to implement, 
    but ensures that the required payment details you collect from the shopper are up-to-date.
    
    Also you will always have up-to-date available payment methods.

Docs: https://docs.adyen.com/checkout/api-only?tab=%23codeBlockuKMzI_bash#step-2-collect-shopper-details

#### Collecting your shopper's card details

    If the shopper selects to pay with a Card payment method, 
    you have the following options for collecting card details:

        - If you are fully PCI compliant, you can collect your shopper's raw card data.
        - Otherwise, you need to collect and encrypt your shopper's card data with our Custom Card Component.

    After you have collected your shopper's raw card details or you have received the encrypted details 
    from the Custom Card fields component, pass the data along with type: scheme to your server and proceed to step 3.

    Encrypted shopper payment details for Card

      {
          "type":"scheme",
          "encryptedCardNumber":"adyenjs_0_1_18$MT6ppy0FAMVMLH...",
          "encryptedExpiryMonth":"adyenjs_0_1_18$MT6ppy0FAMVMLH...",
          "encryptedExpiryYear":"adyenjs_0_1_18$MT6ppy0FAMVMLH...",
          "encryptedSecurityCode":"adyenjs_0_1_18$MT6ppy0FAMVMLH...",
          "holderName" : "S. Hopper"
      }

Docs: https://docs.adyen.com/checkout/api-only?tab=%23codeBlockuKMzI_bash#collect-card-details

### Authorize / Capture

Docs: https://docs.adyen.com/checkout/capture

    For some payment methods, the payment is completed in two steps:
    
    Authorisation – The payment details of the shopper are verified, and the funds are reserved.
    Capture – The reserved funds are transferred from the shopper to your account. 
    By default, payments are captured immediately after authorisation. 
    For payment methods that support separate authorisation and capture, 
    you also have the option to capture the payment later, for example only after the goods have been shipped. 
    This allows you to cancel the payment in case of any issues with the shipment. 
    
    To capture payments, you can use either:   
    
    Automatic capture – payments are captured automatically after authorisation. 
    This is the default setting. You can optionally specify a delay between authorisation and automatic capture.
    Manual capture – capture each payment manually by making a request to the /capture endpoint, 
    or in your Customer Area. You can also schedule a capture by specifying  captureDelayHours in your payment request.

### Payment Authorization

https://docs.adyen.com/payments-fundamentals/payment-glossary#authorisation

    This is the process of the card issuer (like Visa or Mastercard) verifying payment details and reserving the funds to capture it later.
    
    In ecommerce, in-app and point-of-sale payments, authorisation is implemented as an API call to the payment gateway. 
    The gateway and payment processor then perform required validation and risk checks, 
    and ask a corresponding card network to authorise this payment from an issuer to an acquirer. 
    In Adyen API the term "authorisation" is used.
    
    When a payment was authorised but hasn't been captured yet, a merchant can also decide to cancel it 
    for some reason (like a high risk of fraud).
    
    Note that authorisation is valid only for a limited amount of time. 
    In case an authorised payment hasn't been captured or cancelled, 
    it expires after the predefined deadline is missed.
    
    In our case we would prefer to use auto-capture payments. Auto capturing is enabled by default.

### 3D Secure

Docs: https://docs.adyen.com/classic-integration/3d-secure
Docs: https://docs.adyen.com/checkout/3d-secure

    3D Secure is an authentication protocol that provides an additional layer of verification 
    for card-not-present (CNP) transactions. 
    We recommend that you use 3D Secure to comply with authentication regulations for online payments 
    such as PSD2 SCA, and to make use of the liability shift.
    
    3D Secure has two available versions:
    
        3D Secure 1: Shoppers are redirected to the card issuer's site to provide additional authentication data, 
        for example a password or an SMS verification code. 
        The redirection might lead to lower conversion rates due to technical errors during the redirection, 
        or shoppers dropping out of the authentication process.
        
        3D Secure 2: New version expected to replace 3D Secure 1 by 2020/2021. 
        The card issuer performs the authentication within your website or mobile app using passive, biometric, 
        and two-factor authentication approaches. For more information, refer to 3D Secure 2 authentication flows.

    Until card schemes fully decommission 3D Secure 1 by 2020/2021, 
    we expect that some issuing banks will continue to support and require 3D Secure 1. 
    This means that you should be able to handle both 3D Secure 1 and 3D Secure 2 authentication on your checkout page.

### Redirect 3DS API-only integration

Docs: https://docs.adyen.com/checkout/3d-secure/redirect-3ds2-3ds1/api-only

### Used technologies:

- Spring Boot
- Feign as declarative REST client
- Consul for Service Discovery
- Hystrix as a latency and fault tolerance library
- Ribbon for client side load balancing **(?)**
- Flyway as Database migration tool
- PostgreSQL as RDBMS