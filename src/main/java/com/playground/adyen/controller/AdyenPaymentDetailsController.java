package com.playground.adyen.controller;

import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.dto.AdyenPaymentDetailsRequestDTO;
import com.playground.adyen.service.AdyenPaymentDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/adyen-api/payment")
@RestController
public class AdyenPaymentDetailsController {

    private final AdyenPaymentDetailsService paymentDetailsService;

    @PostMapping("/details")
    public PaymentsResponse sendPaymentDetails(@RequestBody AdyenPaymentDetailsRequestDTO request) {
        log.info("Request to send payment details");
        return paymentDetailsService.sendPaymentDetails(request);
    }
}
