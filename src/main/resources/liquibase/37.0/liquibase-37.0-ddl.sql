CREATE TABLE permission(
id int8 generated always as IDENTITY,
packet_id int4,
permission_name varchar(50),
CONSTRAINT pm_packet
FOREIGN KEY(packet_id)
REFERENCES packet(id),
PRIMARY KEY(id)
)