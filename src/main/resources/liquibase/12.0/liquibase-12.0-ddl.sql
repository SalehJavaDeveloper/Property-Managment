CREATE TABLE tenant(
    id int8 generated always as IDENTITY,
    unit_id int4 NOT NULL,
    name varchar(30) NOT NULL,
    surname varchar(30) NOT NULL,
    father_name varchar(30) ,
    phone_number1 varchar(15) NOT NULL,
    phone_number2 varchar(15),
    home_number varchar(15) ,
    wp_phone_number1 boolean,
    call_phone_number1 boolean ,
    sms_phone_number1 boolean ,
    wp_phone_number2 boolean ,
    call_phone_number2 boolean ,
    sms_phone_number2 boolean ,
    email varchar(20) NOT NULL,
    pin varchar(15) NOT NULL,
    activate BOOLEAN,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    PRIMARY KEY(id),
    CONSTRAINT pm_unit
    FOREIGN KEY(unit_id)
    REFERENCES unit(id)
)