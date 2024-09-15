CREATE table building(
                         id int8 generated always as identity,
                         name varchar(30) NOT NULL,
                         description varchar(50) NOT NULL,
                         count_unit int4 NOT NULL,
                         count_entrance int4 NOT NULL,
                         count_floor int4 NOT NULL,
                         area float8 NOT NULL,
                         note varchar(100) NOT NULL,
                         property_id int4 NOT NULL,
                         created_by BIGINT,
                         created_date timestamp(6),
                         last_modified_by BIGINT,
                         last_modified_date timestamp(6),
                         activate BOOLEAN,
                         PRIMARY KEY(id),
                         CONSTRAINT pm_property
                     FOREIGN KEY(property_id)
                     REFERENCES property(id)
)