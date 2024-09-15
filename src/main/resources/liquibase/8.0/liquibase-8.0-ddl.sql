CREATE TABLE street(
                        id int8 generated always as IDENTITY,
                        district_id int4 NOT NULL,
                        street_name varchar(30) NOT NULL,
                        created_by BIGINT,
                        created_date timestamp(6),
                        last_modified_by BIGINT,
                        last_modified_date timestamp(6),
                        activate BOOLEAN,
                        PRIMARY KEY(id),
                        CONSTRAINT pm_district
                   FOREIGN KEY(district_id)
                   REFERENCES district(id)
)