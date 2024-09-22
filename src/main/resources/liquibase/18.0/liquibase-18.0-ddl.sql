CREATE TABLE payment_building (
                                  id SERIAL PRIMARY KEY,
                                  building_id BIGINT REFERENCES building(id),
                                  payment_id BIGINT REFERENCES payment(id)
);