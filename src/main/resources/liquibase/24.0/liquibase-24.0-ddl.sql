CREATE TABLE communication_unit(
id int8 GENERATED ALWAYS AS IDENTITY,
unit_id int4,
communication_id int4,
activate BOOLEAN,
created_by BIGINT,
created_date timestamp(6),
last_modified_by BIGINT,
last_modified_date timestamp(6),
CONSTRAINT pm_unit
FOREIGN KEY(unit_id)
REFERENCES postgres.public.unit(id),
CONSTRAINT pm_communication
FOREIGN KEY(communication_id)
REFERENCES communication(id)
)