CREATE table property_responsible_person(
    id int8 GENERATED ALWAYS AS IDENTITY,
    property_id int4 NOT NULL,
    response_person_id int4 NOT NULL,
    created_by BIGINT,
    created_date timestamp(6),
    last_modified_by BIGINT,
    last_modified_date timestamp(6),
    activate BOOLEAN,
    PRIMARY KEY(id),
    CONSTRAINT pm_property
    FOREIGN KEY(property_id)
    REFERENCES property(id),
    CONSTRAINT pm_user
    FOREIGN KEY(response_person_id)
    REFERENCES users(id)
)