package com.playground.adyen.controller;

import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.dto.AdyenEncryptedCCPaymentRequestDTO;
import com.playground.adyen.dto.AdyenTransactionDTO;
import com.playground.adyen.service.AdyenPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/adyen-api/payment")
@RestController
public class AdyenPaymentController {

    private final AdyenPaymentService paymentService;

    @PostMapping(
            path = "/credit-card",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PaymentsResponse payWithCreditCard(@RequestBody AdyenEncryptedCCPaymentRequestDTO request) {
        log.info("Request to initiate Adyen credit card payment [{}]", request.getPaymentPayload());
        return paymentService.payWithCreditCard(request);
    }

    @GetMapping(path = "/all")
    public List<AdyenTransactionDTO> getAll(@RequestParam(required = false) Integer limit) {
        log.info("Request to get all payments");
        return paymentService.getAll(limit);
    }
}
