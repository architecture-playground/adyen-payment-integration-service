package com.playground.adyen.service;

import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.client.AdyenJavaApiClient;
import com.playground.adyen.dto.AdyenPaymentDetailsRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdyenPaymentDetailsService {

    private final AdyenJavaApiClient adyenClient;

    public PaymentsResponse sendPaymentDetails(AdyenPaymentDetailsRequestDTO request) {
        return adyenClient.sendPaymentDetails(
                request.getPaymentData(),
                request.getFingerprint(),
                request.getPaymentDetails()
        );
    }
}
