CREATE TABLE transaction (
                             id int8 GENERATED ALWAYS AS IDENTITY,
                             amount INT NOT NULL,
                             installment INT NOT NULL ,
                             description VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL,
                             telephone VARCHAR(255) NOT NULL,
                             memberId VARCHAR(255) NOT NULL,
                             orderId VARCHAR(255) NOT NULL,
                             PRIMARY KEY(id)
);