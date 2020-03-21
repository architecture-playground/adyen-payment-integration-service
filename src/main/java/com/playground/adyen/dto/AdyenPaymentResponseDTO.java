package com.playground.adyen.dto;

import com.adyen.model.checkout.Redirect;
import lombok.Data;

/**
 * All possible response parameters listed here
 * <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/payments__section_resParams">Response Documentation</a>
 */
@Data
public class AdyenPaymentResponseDTO {

    /**
     * A token to pass to the 3DS2 Component to get the fingerprint/challenge.
     */
    private String token;

    /**
     * Enum that specifies the action that needs to be taken by the client.
     */
    private String type;

    /**
     * Specifies the URL to redirect to.
     */
    private String url;

    /**
     * When non-empty, contains a value that you must submit to the /payments/details endpoint.
     */
    private String paymentData;

    /**
     * Adyen's 16-character string reference associated with the transaction/request.
     * This value is globally unique; quote it when communicating with us about this request.
     * <p>
     * pspReference is returned only for non-redirect payment methods.
     */
    private String pspReference;

    /**
     * When the payment flow requires a redirect, this object contains information about the redirect URL.
     */
    private Redirect redirect;

    /**
     * If the payment's authorisation is refused or an error occurs during authorisation,
     * this field holds Adyen's mapped reason for the refusal or a description of the error.
     * <p>
     * When a transaction fails, the authorisation response includes resultCode and refusalReason values.
     */
    private String refusalReason;

    /**
     * Code that specifies the refusal reason. For more information, see Authorisation refusal reasons.
     */
    private String refusalReasonCode;

    /**
     * The result of the payment.
     *
     * @see <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/payments__resParam_resultCode">The full list of possible values see here.</a>
     */
    private String resultCode;
}
