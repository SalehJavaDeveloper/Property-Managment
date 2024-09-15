CREATE TABLE users(
    id int8 GENERATED ALWAYS AS IDENTITY,
    email varchar(70) NOT NULL,
    password varchar(100) NOT NULL,
    full_name varchar(100) NOT NULL,
    username varchar(100) NOT NULL,
    company_id int4,
    phone_number1 varchar(30) NOT NULL,
    phone_number2 varchar(100) NOT NULL,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    activate BOOLEAN,
    CONSTRAINT pm_company
    FOREIGN KEY(company_id)
    REFERENCES company(id),
    PRIMARY KEY(id)
)