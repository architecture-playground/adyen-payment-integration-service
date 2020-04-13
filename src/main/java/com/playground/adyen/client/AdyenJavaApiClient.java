package com.playground.adyen.client;


import com.adyen.Client;
import com.adyen.model.Amount;
import com.adyen.model.ApiError;
import com.adyen.model.BrowserInfo;
import com.adyen.model.checkout.DefaultPaymentMethodDetails;
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

import java.util.Map;
import java.util.Optional;

import static com.adyen.constants.BrandCodes.SEPA_DIRECT_DEBIT;
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
                                                   BrowserInfo browserInfo,
                                                   String shopperIp) {

        return wrapAdyenCall(() -> {
            log.info("Initiate encrypted CC payment with Adyen Java client. Reference {}", reference);

            Checkout checkout = new Checkout(adyenClient);

            PaymentsRequest paymentsRequest = buildBasePaymentsRequest(currency, amountInMinorUnits, reference);
            paymentsRequest.addEncryptedCardData(encryptedCardNumber, encryptedExpiryMonth, encryptedExpiryYear, encryptedSecurityCode, holderName);

            if (nonNull(browserInfo)) {
                paymentsRequest.setBrowserInfo(browserInfo);
                paymentsRequest.setShopperIP(shopperIp);
                paymentsRequest.setChannel(PaymentsRequest.ChannelEnum.WEB);
                paymentsRequest.setOrigin(adyenProps.getOrigin());
            }

            return checkout.payments(paymentsRequest);
        });
    }

    public PaymentsResponse sendSepaPayment(String reference,
                                            String currency,
                                            Long amountInMinorUnits,
                                            String ownerName,
                                            String ibanNumber) {
        return wrapAdyenCall(() -> {
            log.info("Initiate sepa dd payment with Adyen Java client. Reference {}", reference);

            Checkout checkout = new Checkout(adyenClient);
            PaymentsRequest paymentsRequest = buildBasePaymentsRequest(currency, amountInMinorUnits, reference);

            DefaultPaymentMethodDetails paymentMethodDetails = new DefaultPaymentMethodDetails();
            paymentMethodDetails.setSepaOwnerName(ownerName);
            paymentMethodDetails.setSepaIbanNumber(ibanNumber);
            paymentMethodDetails.setType(SEPA_DIRECT_DEBIT);
            paymentsRequest.setPaymentMethod(paymentMethodDetails);

            return checkout.payments(paymentsRequest);
        });
    }

    public PaymentsResponse sendPaymentDetails(String paymentData, Map<String, String> paymentDetails) {
        return wrapAdyenCall(() -> {
            log.info("Send payment details");

            Checkout checkout = new Checkout(adyenClient);
            PaymentsDetailsRequest paymentsDetailsRequest = new PaymentsDetailsRequest()
                    .details(paymentDetails)
                    .paymentData(paymentData);

            return checkout.paymentsDetails(paymentsDetailsRequest);
        });
    }

    private PaymentsRequest buildBasePaymentsRequest(String currency, long amountInMinorUnits, String reference) {
        PaymentsRequest paymentsRequest = new PaymentsRequest();
        paymentsRequest.setMerchantAccount(adyenProps.getMerchantAccount());

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setValue(amountInMinorUnits);
        paymentsRequest.setAmount(amount);

        paymentsRequest.setReference(reference);
        return paymentsRequest;
    }

    private <T> T wrapAdyenCall(ExceptionalSupplier<T, Exception> dataFetcher) {
        try {
            return dataFetcher.get();
        } catch (ApiException ex) {
            log.info("Failed send payment to Adyen. Status: {}, Error: {}", ex.getStatusCode(), ex.getError());
            throw new AdyenApiException(extractMessage(ex));
        } catch (Exception ex) {
            log.info("Failed send payment to Adyen", ex);
            throw new AdyenApiException("Unexpected Adyen Exception");
        }
    }

    private String extractMessage(ApiException ex) {
        return Optional.ofNullable(ex.getError())
                .map(ApiError::getMessage)
                .orElse("Unknown Adyen API exception.");
    }

    @FunctionalInterface
    private interface ExceptionalSupplier<T, E extends Exception> {
        T get() throws E;
    }
}
