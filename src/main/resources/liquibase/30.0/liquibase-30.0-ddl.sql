CREATE SEQUENCE payment_unit2_id_seq START 1;

CREATE TABLE payment_unit2
(
    id         BIGINT DEFAULT nextval('payment_unit2_id_seq') PRIMARY KEY,
    unit_id    BIGINT REFERENCES unit (id),
    payment_id BIGINT REFERENCES payment2 (id)
);
