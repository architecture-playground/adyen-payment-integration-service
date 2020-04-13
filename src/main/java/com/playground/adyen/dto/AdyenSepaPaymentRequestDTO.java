package com.playground.adyen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdyenSepaPaymentRequestDTO extends AdyenBasePaymentRequestDTO {

    private String ownerName;
    private String ibanNumber;
}
