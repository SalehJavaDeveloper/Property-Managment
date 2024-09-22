CREATE TABLE transaction2
(
    id                  UUID PRIMARY KEY,
    payment_unit_id     BIGINT REFERENCES payment_unit2 (id),
    payment_provider_id BIGINT REFERENCES payment_provider (id),
    status              VARCHAR(255),
    url                 VARCHAR(255),
    update_date         TIMESTAMP,
    payment_date        TIMESTAMP
);