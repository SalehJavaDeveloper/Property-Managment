CREATE TABLE communication(
id int8 GENERATED ALWAYS AS IDENTITY,
delivery_date date,
subject varchar(500),
content varchar(500),
message_status varchar(30),
activate BOOLEAN,
created_by BIGINT,
created_date timestamp(6),
last_modified_by BIGINT,
last_modified_date timestamp(6),
PRIMARY KEY(id)
)