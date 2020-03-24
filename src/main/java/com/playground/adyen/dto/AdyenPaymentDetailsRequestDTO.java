package com.playground.adyen.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * This DTO represents request described at <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/payments/details__section_reqParams">docs</>
 */
@Data
public class AdyenPaymentDetailsRequestDTO {

    /**
     * Use this collection to submit the details that were returned as a result of the /payments call.
     */
    @NotNull
    private Map<String, String> paymentDetails;

    /**
     * The paymentData value that you received in the response to the /payments call.
     */
    private String paymentData;

    private String fingerprint;
}
