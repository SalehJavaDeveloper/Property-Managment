CREATE TABLE communication(
id int8 GENERATED ALWAYS AS IDENTITY,
channel_id int4 NOT NULL,
property_id int4 NOT NULL,
building_id int4 NOT NULL,
unit_id int4 NOT NULL,
delivery_date date,
subject varchar(500),
content varchar(500),
message_status varchar(30),
activate BOOLEAN,
created_by BIGINT,
created_date timestamp(6),
last_modified_by BIGINT,
last_modified_date timestamp(6),
-- CONSTRAINT pm_channel
-- FOREIGN KEY(channel_id)
-- REFERENCES communication_channel(id),
-- CONSTRAINT pm_property
-- FOREIGN KEY(property_id)
-- REFERENCES communication_property(id),
-- CONSTRAINT pm_building
-- FOREIGN KEY(building_id)
-- REFERENCES communication_building(id),
-- CONSTRAINT pm_unit
-- FOREIGN KEY(unit_id)
-- REFERENCES communication_unit(id),
PRIMARY KEY(id)
)