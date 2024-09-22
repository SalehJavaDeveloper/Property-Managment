CREATE TABLE payment_property (
                                  id SERIAL PRIMARY KEY,
                                  property_id BIGINT REFERENCES property(id),
                                  payment_id BIGINT REFERENCES payment(id)
);