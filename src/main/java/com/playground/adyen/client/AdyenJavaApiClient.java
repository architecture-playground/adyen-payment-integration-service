package com.playground.adyen.client;


import com.adyen.Client;
import com.adyen.model.Amount;
import com.adyen.model.checkout.PaymentMethodsRequest;
import com.adyen.model.checkout.PaymentMethodsResponse;
import com.adyen.service.Checkout;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * Docs: https://github.com/Adyen/adyen-java-api-library
 */
@RequiredArgsConstructor
@Service
public class AdyenJavaApiClient {

    private final Client adyenClient;

    /**
     * {@link Client} can be used for sending request.
     * But we would prefer to create Feign client around Adyen REST API
     *
     * @see AdyenApi
     */
    @SneakyThrows
    public PaymentMethodsResponse getAvailablePaymentMethods() {
        Checkout checkout = new Checkout(adyenClient);

        PaymentMethodsRequest paymentMethodsRequest = new PaymentMethodsRequest();

        paymentMethodsRequest.setMerchantAccount("YOUR_MERCHANT_ACCOUNT");
        paymentMethodsRequest.setCountryCode("NL");

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setValue(1000L);
        paymentMethodsRequest.setAmount(amount);

        paymentMethodsRequest.setChannel(PaymentMethodsRequest.ChannelEnum.WEB);

        return checkout.paymentMethods(paymentMethodsRequest);
    }
}
