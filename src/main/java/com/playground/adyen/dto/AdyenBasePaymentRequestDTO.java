package com.playground.adyen.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public abstract class AdyenBasePaymentRequestDTO {

    private String paymentPayload;

    @NotNull
    private String currency;

    @NotNull
    private long amountInMinorUnits;
}
