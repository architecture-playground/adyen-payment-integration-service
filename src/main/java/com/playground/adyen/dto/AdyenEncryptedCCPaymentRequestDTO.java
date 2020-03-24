package com.playground.adyen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CC in name means CreditCard
 * <p>
 * The collection that contains the type of the payment method and its specific information (e.g. idealIssuer).
 * Can be represented as <type>Map&lt;String, Object$gt;</type>
 * <p>
 * Please note that this part of payment request can be dramatically different for different payment methods
 * <p>
 * This particular DTO represent payment method info for simple Credit Card payment.
 * Read more here: <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/payments__example_payments-card-direct">Example payments credit card</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdyenEncryptedCCPaymentRequestDTO extends AdyenBasePaymentRequestDTO {

    private String holderName;
    private String encryptedCardNumber;
    private String encryptedExpiryMonth;
    private String encryptedExpiryYear;
    private String encryptedSecurityCode;
}
