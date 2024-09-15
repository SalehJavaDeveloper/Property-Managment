CREATE TABLE task (
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      property_id BIGINT,
                      building_id BIGINT,
                      unit_id BIGINT,
                      user_id BIGINT,
                      task_type VARCHAR(255),
                      due_time TIMESTAMP,
                      subject VARCHAR(255),
                      description VARCHAR(255),
                      created_by BIGINT,
                      created_date TIMESTAMP,
                      last_modified_by BIGINT,
                      last_modified_date TIMESTAMP,
                      FOREIGN KEY (property_id) REFERENCES property (id),
                      FOREIGN KEY (building_id) REFERENCES building (id),
                      FOREIGN KEY (unit_id) REFERENCES unit (id),
                      FOREIGN KEY (user_id) REFERENCES users (id)
);