CREATE TABLE media_file (
                            id uuid PRIMARY KEY,
                            file_path VARCHAR(255),
                            media_file_type VARCHAR(255),
                            created_by BIGINT,
                            created_date TIMESTAMP,
                            last_modified_by BIGINT,
                            last_modified_date TIMESTAMP,
                            activate BOOLEAN,
                            task_id BIGINT,
                            FOREIGN KEY (task_id) REFERENCES task (id)
);