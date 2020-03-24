package com.playground.adyen.model;

import com.adyen.model.checkout.CheckoutPaymentsAction;
import com.adyen.model.checkout.PaymentsResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "adyen", name = "transaction")
@Entity
public class AdyenTransaction {

    public AdyenTransaction(String paymentPayload) {
        this.paymentPayload = paymentPayload;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "pg-uuid")
    private UUID id;

    @Type(type = "jsonb")
    @Column(name = "payment_payload")
    private String paymentPayload;

    @Type(type = "pgsql-enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "result_code")
    private PaymentsResponse.ResultCodeEnum resultCode;

    @Column(name = "psp_reference")
    private String pspReference;

    @Column(name = "refusal_reason")
    private String refusalReason;

    @Column(name = "refusal_reason_code")
    private String refusalReasonCode;

    @Type(type = "pgsql-enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private CheckoutPaymentsAction.CheckoutActionType actionType;

    @Column(name = "action_url")
    private String actionUrl;

    //// AUTO-GENERATED

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

}
