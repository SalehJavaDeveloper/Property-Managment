CREATE TABLE city(
                        id int8 generated always as IDENTITY,
                        country_id int4 NOT NULL ,
                        city_name varchar(30) NOT NULL,
                        created_by BIGINT,
                        created_date timestamp(6),
                        last_modified_by BIGINT,
                        last_modified_date timestamp(6),
                        activate BOOLEAN,
                        PRIMARY KEY(id),
                        Constraint fk_country
                 FOREIGN KEY(country_id)
                 REFERENCES country(id)
)