package com.playground.adyen.service;

import com.adyen.model.BrowserInfo;
import com.adyen.model.checkout.CheckoutPaymentsAction;
import com.adyen.model.checkout.PaymentsResponse;
import com.playground.adyen.client.AdyenJavaApiClient;
import com.playground.adyen.converter.AdyenBrowserInfoConverter;
import com.playground.adyen.converter.AdyenTransactionConverter;
import com.playground.adyen.dto.*;
import com.playground.adyen.model.AdyenTransaction;
import com.playground.adyen.repository.AdyenTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class AdyenPaymentService {

    private final AdyenJavaApiClient adyenClient;
    private final AdyenBrowserInfoConverter browserInfoConverter;
    private final AdyenTransactionConverter adyenTransactionConverter;
    private final AdyenTransactionRepository transactionRepository;

    public PaymentsResponse payWithCreditCard(AdyenEncryptedCCPaymentRequestDTO request) {
        AdyenTransaction transaction = transactionRepository.save(new AdyenTransaction(request.getPaymentPayload()));

        Adyen3DSecureInfoDTO adyen3DSecureInfo = request.getAdyen3DSecureInfoDTO();
        BrowserInfo browserInfo = null;
        String shopperIp = null;
        if (nonNull(adyen3DSecureInfo)) {
            browserInfo = browserInfoConverter.from3DSecureInfo(adyen3DSecureInfo);
            shopperIp = adyen3DSecureInfo.getShopperIp();
        }

        PaymentsResponse response = adyenClient.sendEncryptedCCPayment(
                request.getCurrency(),
                request.getAmountInMinorUnits(),
                request.getEncryptedCardNumber(),
                request.getEncryptedExpiryMonth(),
                request.getEncryptedExpiryYear(),
                request.getEncryptedSecurityCode(),
                request.getHolderName(),
                transaction.getId().toString(),
                browserInfo,
                shopperIp
        );

        updateTransactionState(transaction, response);
        return response;
    }

    public PaymentsResponse payWithSepa(AdyenSepaPaymentRequestDTO request) {
        AdyenTransaction transaction = transactionRepository.save(new AdyenTransaction(request.getPaymentPayload()));

        PaymentsResponse response = adyenClient.sendSepaPayment(
                transaction.getId().toString(),
                request.getCurrency(),
                request.getAmountInMinorUnits(),
                request.getOwnerName(),
                request.getIbanNumber()
        );

        updateTransactionState(transaction, response);
        return response;
    }

    public PaymentsResponse payWithPaypal(AdyenPaypalPaymentRequestDTO request) {
        AdyenTransaction transaction = transactionRepository.save(new AdyenTransaction(request.getPaymentPayload()));

        PaymentsResponse response = adyenClient.sendPaypalPayment(
                transaction.getId().toString(),
                request.getCurrency(),
                request.getAmountInMinorUnits(),
                "John Smith",
                request.getReturnUrl()
        );

        updateTransactionState(transaction, response);
        return response;
    }


    public List<AdyenTransactionDTO> getAll(Integer limit) {
        if (limit == null) {
            limit = Integer.MAX_VALUE;
        }

        return transactionRepository.findAll(PageRequest.of(0, limit)).get()
                .map(adyenTransactionConverter::toDto)
                .collect(Collectors.toList());
    }

    private void updateTransactionState(AdyenTransaction transaction, PaymentsResponse response) {
        transaction.setResultCode(response.getResultCode().getValue());
        transaction.setPspReference(response.getPspReference());

        transaction.setRefusalReason(response.getRefusalReason());
        transaction.setRefusalReasonCode(response.getRefusalReasonCode());

        CheckoutPaymentsAction action = response.getAction();
        if (nonNull(action)) {
            transaction.setActionType(action.getType().getValue());
            transaction.setActionUrl(action.getUrl());
        }

        transactionRepository.save(transaction);
    }
}
