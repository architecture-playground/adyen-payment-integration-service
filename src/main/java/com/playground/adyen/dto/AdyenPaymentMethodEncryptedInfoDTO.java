package com.playground.adyen.dto;

import lombok.Data;

@Data
public class AdyenPaymentMethodEncryptedInfoDTO {

    private String type;
    private String encryptedCardNumber;
    private String encryptedExpiryMonth;
    private String encryptedExpiryYear;
    private String encryptedSecurityCode;
}
