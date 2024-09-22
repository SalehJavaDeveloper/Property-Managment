CREATE TABLE user_positions
(
    id   int8 GENERATED ALWAYS AS IDENTITY,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
)