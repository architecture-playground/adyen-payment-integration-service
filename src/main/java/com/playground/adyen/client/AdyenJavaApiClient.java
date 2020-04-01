package com.playground.adyen.client;


import com.adyen.Client;
import com.adyen.model.Amount;
import com.adyen.model.BrowserInfo;
import com.adyen.model.checkout.PaymentsDetailsRequest;
import com.adyen.model.checkout.PaymentsRequest;
import com.adyen.model.checkout.PaymentsResponse;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import com.playground.adyen.exception.AdyenApiException;
import com.playground.adyen.props.AdyenProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Docs: https://github.com/Adyen/adyen-java-api-library
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AdyenJavaApiClient {

    private final Client adyenClient;
    private final AdyenProperties adyenProps;

    public PaymentsResponse sendEncryptedCCPayment(String currency,
                                                   long amountInMinorUnits,
                                                   String encryptedCardNumber,
                                                   String encryptedExpiryMonth,
                                                   String encryptedExpiryYear,
                                                   String encryptedSecurityCode,
                                                   String holderName,
                                                   String reference,
                                                   String returnUrl,
                                                   BrowserInfo browserInfo,
                                                   String shopperIp) {
        try {
            log.info("Initiate encrypted CC payment with Adyen Java client. Reference {}", reference);

            Checkout checkout = new Checkout(adyenClient);

            PaymentsRequest paymentsRequest = new PaymentsRequest();
            paymentsRequest.setMerchantAccount(adyenProps.getMerchantAccount());

            Amount amount = new Amount();
            amount.setCurrency(currency);
            amount.setValue(amountInMinorUnits);
            paymentsRequest.setAmount(amount);

            paymentsRequest.setReference(reference);
            paymentsRequest.addEncryptedCardData(encryptedCardNumber, encryptedExpiryMonth, encryptedExpiryYear, encryptedSecurityCode, holderName);
            paymentsRequest.setReturnUrl(returnUrl);

            if (nonNull(browserInfo)) {
                paymentsRequest.setBrowserInfo(browserInfo);
                paymentsRequest.setShopperIP(shopperIp);
                paymentsRequest.setChannel(PaymentsRequest.ChannelEnum.WEB);
                paymentsRequest.setOrigin(adyenProps.getOrigin());
            }

            return checkout.payments(paymentsRequest);
        } catch (ApiException ex) {
            log.info("Adyen responded with error. Status: {}, Error: {}", ex.getStatusCode(), ex.getError());
            throw new AdyenApiException(ex.getError().getMessage());
        } catch (IOException ex) {
            log.info("Failed send payment to Adyen", ex);
            throw new AdyenApiException("Unexpected I/O exception");
        }
    }

    public PaymentsResponse sendPaymentDetails(String paymentData, Map<String, String> paymentDetails) {
        try {
            log.info("Send payment details");

            Checkout checkout = new Checkout(adyenClient);
            PaymentsDetailsRequest paymentsDetailsRequest = new PaymentsDetailsRequest()
                    .details(paymentDetails)
                    .paymentData(paymentData);

            return checkout.paymentsDetails(paymentsDetailsRequest);
        } catch (ApiException ex) {
            log.info("Adyen responded with error. Status: {}, Error: {}", ex.getStatusCode(), ex.getError());
            throw new AdyenApiException(ex.getError().getMessage());
        } catch (IOException ex) {
            log.info("Failed send payment to Adyen", ex);
            throw new AdyenApiException("Unexpected I/O exception");
        }
    }
}
