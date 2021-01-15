package com.playground.adyen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdyenPaypalPaymentRequestDTO extends AdyenBasePaymentRequestDTO {

    private String returnUrl;
}
