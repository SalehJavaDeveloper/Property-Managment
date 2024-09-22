CREATE TABLE communication_main(
                    id int8 generated always as IDENTITY,
                    property_id int4,
                    channel_type varchar(20),
                    local_date_time timestamp,
                    subject varchar(300),
                    content varchar(300),
                    note varchar(300),
                    cancel boolean,
                    message_status varchar(30),
                    message_building_all varchar(20),
                    message_unit_all varchar(30),
                    CONSTRAINT pm_property
                               FOREIGN KEY (property_id)
                               REFERENCES property(id),
                               PRIMARY KEY (id)
);

CREATE TABLE communication_main_building_entity (
    id int8 generated always as IDENTITY,
    com_building_id int4,
    building_id int4,
    PRIMARY KEY (id)
);

CREATE TABLE communication_main_unit_entity(
id int8 generated always as IDENTITY,
com_unit_id int4,
unit_id int4,
PRIMARY KEY (id)
)
