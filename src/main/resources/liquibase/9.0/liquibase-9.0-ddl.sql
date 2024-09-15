CREATE TABLE property(
                         id int8 generated always as IDENTITY,
                         name varchar(30) NOT NULL,
                         property_type varchar(20) NOT NULL,
                         country_id int4 NOT NULL,
                         city_id int4 NOT NULL,
                         district_id int4 NOT NULL,
                         village_id int4 NOT NULL,
                         street_id int4 NOT NULL,
                         address_note varchar(100) NOT NULL,
                         count_building int4 NOT NULL,
                         count_unit int4 NOT NULL,
                         bank_account varchar(50) NOT NULL,
                         email varchar(40) NOT NULL,
                         area float NOT NULL,
                         created_by BIGINT,
                         created_date timestamp(6),
                         last_modified_by BIGINT,
                         last_modified_date timestamp(6),
                         activate BOOLEAN,
                         PRIMARY KEY(id),
                         CONSTRAINT pm_country
                     FOREIGN KEY(country_id)
                     REFERENCES country(id),
                     CONSTRAINT pm_city
                     FOREIGN KEY(city_id)
                     REFERENCES city(id)
                     On DELETE SET NULL,
                     CONSTRAINT pm_district
                     FOREIGN KEY(district_id)
                     REFERENCES district(id),
                     CONSTRAINT pm_village
                     FOREIGN KEY(village_id)
                     REFERENCES village(id),
                     CONSTRAINT pm_street
                     FOREIGN KEY(street_id)
                     REFERENCES street(id)
)