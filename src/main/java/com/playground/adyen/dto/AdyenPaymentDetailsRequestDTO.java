package com.playground.adyen.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AdyenPaymentDetailsRequestDTO {

    /**
     * The paymentData value that you received in the response to the /payments call.
     */
    private String paymentData;

    /**
     * The paymentData value that you received in the response to the /payments call.
     */
    private Map<String, Object> details;
}
