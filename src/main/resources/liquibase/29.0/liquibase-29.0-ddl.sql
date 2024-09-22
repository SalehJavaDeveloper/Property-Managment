CREATE TABLE payment2
(
    id             SERIAL PRIMARY KEY,
    payment_type   VARCHAR(255),
    recurring_date DATE,
    payment_date   DATE,
    amount         NUMERIC(19, 2),
    due_date       DATE,
    description    VARCHAR(255),
    notes          VARCHAR(255),
    service_type   VARCHAR(255),
    create_date    TIMESTAMP,
    update_date    TIMESTAMP
);