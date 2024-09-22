CREATE TABLE communication_building(
id int8 GENERATED ALWAYS AS IDENTITY,
building_id int4,
communication_id int4,
activate BOOLEAN,
created_by BIGINT,
created_date timestamp(6),
last_modified_by BIGINT,
last_modified_date timestamp(6),
CONSTRAINT pm_building
FOREIGN KEY(building_id)
REFERENCES postgres.public.building(id),
CONSTRAINT pm_communication
FOREIGN KEY(communication_id)
REFERENCES communication(id)
)