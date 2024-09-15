CREATE TABLE payment_unit (
                              id SERIAL PRIMARY KEY,
                              unit_id BIGINT REFERENCES unit(id),
                              payment_id BIGINT REFERENCES payment(id)
);