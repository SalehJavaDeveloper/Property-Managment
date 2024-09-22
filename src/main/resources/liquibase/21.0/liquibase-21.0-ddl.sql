CREATE TABLE communication_channel(
    id int8 GENERATED ALWAYS AS IDENTITY,
    channel_type varchar(30),
    communication_id int4,
    activate BOOLEAN,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    CONSTRAINT pm_communication
    FOREIGN KEY(communication_id)
    REFERENCES communication(id)
)