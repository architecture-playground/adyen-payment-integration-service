package com.playground.adyen.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public abstract class AdyenBasePaymentRequestDTO {

    @NotNull
    private String paymentPayload;

    @NotNull
    private String currency;

    @NotNull
    private long amountInMinorUnits;

    /**
     * The URL to return to in case of a redirection. The format depends on the channel.
     */
    @NotNull
    private String returnUrl;
}
