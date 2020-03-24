package com.playground.adyen.service;

import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.client.AdyenJavaApiClient;
import com.playground.adyen.dto.AdyenEncryptedCCPaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdyenPaymentService {

    private final AdyenJavaApiClient adyenClient;

    public PaymentsResponse payWithCreditCard(AdyenEncryptedCCPaymentRequestDTO request) {
        return adyenClient.sendEncryptedCCPayment(
                request.getCurrency(),
                request.getAmountInMinorUnits(),
                request.getEncryptedCardNumber(),
                request.getEncryptedExpiryMonth(),
                request.getEncryptedExpiryYear(),
                request.getEncryptedSecurityCode(),
                request.getHolderName(),
                request.getReference(),
                request.getReturnUrl()
        );
    }
}
