package com.playground.adyen.service;

import com.adyen.model.checkout.CheckoutPaymentsAction;
import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.client.AdyenJavaApiClient;
import com.playground.adyen.converter.AdyenBrowserInfoConverter;
import com.playground.adyen.dto.Adyen3DSecureInfoDTO;
import com.playground.adyen.dto.AdyenEncryptedCCPaymentRequestDTO;
import com.playground.adyen.model.AdyenTransaction;
import com.playground.adyen.repository.AdyenTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class AdyenPaymentService {

    private final AdyenJavaApiClient adyenClient;
    private final AdyenBrowserInfoConverter browserInfoConverter;
    private final AdyenTransactionRepository transactionRepository;

    public PaymentsResponse payWithCreditCard(AdyenEncryptedCCPaymentRequestDTO request) {
        Adyen3DSecureInfoDTO adyen3DSecureInfo = request.getAdyen3DSecureInfoDTO();
        AdyenTransaction transaction = transactionRepository.save(new AdyenTransaction(request.getPaymentPayload()));

        PaymentsResponse response = adyenClient.sendEncryptedCCPayment(
                request.getCurrency(),
                request.getAmountInMinorUnits(),
                request.getEncryptedCardNumber(),
                request.getEncryptedExpiryMonth(),
                request.getEncryptedExpiryYear(),
                request.getEncryptedSecurityCode(),
                request.getHolderName(),
                transaction.getId().toString(),
                request.getReturnUrl(),
                browserInfoConverter.from3DSecureInfo(adyen3DSecureInfo),
                adyen3DSecureInfo.getShopperIp()
        );

        transaction.setResultCode(response.getResultCode());
        transaction.setPspReference(response.getPspReference());

        transaction.setRefusalReason(response.getRefusalReason());
        transaction.setRefusalReasonCode(response.getRefusalReasonCode());

        CheckoutPaymentsAction action = response.getAction();
        if (nonNull(action)) {
            transaction.setActionType(action.getType());
            transaction.setActionUrl(action.getUrl());
        }

        transactionRepository.save(transaction);
        return response;
    }
}
