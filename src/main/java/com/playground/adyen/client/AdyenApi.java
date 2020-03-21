package com.playground.adyen.client;

import com.playground.adyen.dto.AdyenPaymentMethodDTO;
import com.playground.adyen.dto.AdyenPaymentMethodsRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("adyen-api")
public interface AdyenApi {

    /**
     * Queries the available payment methods for a transaction based on the transaction context (like amount, country, and currency).
     * Besides giving back a list of the available payment methods,
     * the response also returns which input details you need to collect from the shopper (to be submitted to /payments).
     * <p>
     * Although we highly recommend using this endpoint to ensure you are always offering the most up-to-date list of payment methods,
     * its usage is optional. You can, for example, also cache the /paymentMethods response and update it once a week.
     *
     * @see <a href="https://docs.adyen.com/api-explorer/#/PaymentSetupAndVerificationService/v52/paymentMethods">POST/paymentMethods</a>
     */
    @PostMapping(
            path = "/paymentMethods",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<AdyenPaymentMethodDTO> getAvailablePaymentMethods(@RequestBody AdyenPaymentMethodsRequestDTO request);
}
