package com.playground.adyen.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public abstract class AdyenBasePaymentRequestDTO {

    @NotNull
    private String currency;

    @NotNull
    private long amountInMinorUnits;

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
}
