CREATE TABLE country(
    id int8 generated always as IDENTITY,
    country_name varchar(30) NOT NULL ,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    activate BOOLEAN,
    PRIMARY KEY(id)
)