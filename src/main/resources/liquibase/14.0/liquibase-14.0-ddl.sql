CREATE TABLE building_responsible_person(
    id int8 GENERATED ALWAYS AS IDENTITY,
    building_id int4 NOT NULL,
    responsible_person_id int4 NOT NULL,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    activate BOOLEAN,
    CONSTRAINT pm_building
    FOREIGN KEY(building_id)
    REFERENCES building(id),
    CONSTRAINT pm_user
    FOREIGN KEY(responsible_person_id)
    REFERENCES users(id)
)