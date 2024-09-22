Create table unit(
    id int8 generated always as IDENTITY,
    building_id int4 NOT NULL,
    count_room int4 NOT NULL,
    area float NOT NULL,
    floor int4 NOT NULL,
    unit_number varchar(10) NOT NULL,
    note varchar(100) NOT NULL,
    unit_type varchar(20) NOT NULL,
    activate BOOLEAN,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    primary key(id),
    CONSTRAINT pm_building
    FOREIGN KEY(building_id)
    REFERENCES building(id)
)