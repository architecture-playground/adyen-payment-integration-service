CREATE TYPE adyen.action_type AS ENUM (
    'redirect',
    'await',
    'qrCode',
    'threeDS2Challenge',
    'threeDS2Fingerprint',
    'voucher',
    'wechatpaySDK'
    );

CREATE TYPE adyen.result_code AS ENUM (
    'AuthenticationFinished',
    'AuthenticationNotRequired',
    'Authorised',
    'Cancelled',
    'ChallengeShopper',
    'Error',
    'IdentifyShopper',
    'Pending',
    'Received',
    'RedirectShopper',
    'Refused',
    'PartiallyAuthorised',
    'PresentToShopper',
    'Unknown'
    );

CREATE TABLE adyen.transaction
(
    id                  UUID                     NOT NULL PRIMARY KEY,
    payment_payload     JSONB                    NOT NULL,
    result_code         adyen.result_code,
    psp_reference       VARCHAR,
    refusal_reason      VARCHAR,
    refusal_reason_code VARCHAR,
    action_type         adyen.action_type,
    action_url          VARCHAR,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL
);