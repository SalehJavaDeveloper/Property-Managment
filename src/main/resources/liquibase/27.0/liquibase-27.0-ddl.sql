CREATE TABLE payment_service_type (   id int8 GENERATED ALWAYS AS IDENTITY,
                                      service_type varchar(50),
                                      payment_id int4,
                                      CONSTRAINT pm_payment
                                          FOREIGN KEY(payment_id)
                                              REFERENCES payment(id)
                                              ON DELETE SET NULL
);