CREATE TABLE adyen.transaction
(
    id                  UUID                     NOT NULL PRIMARY KEY,
    payment_payload     JSONB,
    result_code         VARCHAR,
    psp_reference       VARCHAR,
    refusal_reason      VARCHAR,
    refusal_reason_code VARCHAR,
    action_type         VARCHAR,
    action_url          VARCHAR,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL
);