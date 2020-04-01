package com.playground.adyen.dto;

import com.adyen.model.checkout.CheckoutPaymentsAction;
import com.adyen.model.checkout.PaymentsResponse;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AdyenTransactionDTO {

    private UUID id;
    private String paymentPayload;
    private PaymentsResponse.ResultCodeEnum resultCode;
    private String pspReference;
    private String refusalReason;
    private String refusalReasonCode;
    private CheckoutPaymentsAction.CheckoutActionType actionType;
    private String actionUrl;
    private Instant createdAt;
    private Instant updatedAt;
}
