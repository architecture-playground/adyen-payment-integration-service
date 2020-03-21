package com.playground.adyen.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdyenPaymentsRequestDTO {

    /**
     * The merchant account identifier, with which you want to process the transaction.
     */
    @NotNull
    private String merchantAccount;

    /**
     * The collection that contains the type of the payment method and its specific information (e.g. idealIssuer).
     * Can be represented as <type>Map&lt;String, Object$gt;</type>
     * <p>
     * Please note that this part of payment request can be dramatically different for different payment methods
     * which can be chosen by user in {@link com.playground.adyen.client.AdyenApi#getAvailablePaymentMethods(AdyenPaymentMethodsRequestDTO)}
     *
     * This particular DTO represent payment method info for simple Credit Card payment.
     * Read more here: <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/payments__example_payments-card-direct">Example payments credit card</a>
     */
    @NotNull
    private AdyenPaymentMethodEncryptedInfoDTO paymentMethod;

    /**
     * The reference to uniquely identify a payment.
     * This reference is used in all communication with you about the payment status.
     * We recommend using a unique value per payment; however, it is not a requirement.
     * If you need to provide multiple references for a transaction, separate them with hyphens ("-").
     * Maximum length: 80 characters.
     */
    @NotNull
    private String reference;

    /**
     * The URL to return to in case of a redirection. The format depends on the channel.
     */
    @NotNull
    private String returnUrl;

    /**
     * The shopper's reference to uniquely identify this shopper (e.g. user ID or account ID).
     * <p>
     * This field is required for recurring payments.
     */
    private String shopperReference;
}
