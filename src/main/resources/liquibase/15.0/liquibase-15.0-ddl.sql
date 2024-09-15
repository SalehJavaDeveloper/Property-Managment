
CREATE TABLE payment (
                         id int8 GENERATED ALWAYS AS IDENTITY,
                         payment_type VARCHAR(255),
                         recurring_date DATE,
                         amount DOUBLE PRECISION,
                         due_date DATE,
                         description VARCHAR(255),
                         notes VARCHAR(255),
                         status VARCHAR(50),
                         PRIMARY KEY(id)
);