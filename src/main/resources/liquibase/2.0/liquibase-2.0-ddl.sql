CREATE TABLE usergrantedauthority(
    id int8 GENERATED ALWAYS AS IDENTITY,
    authority varchar(20) NOT NULL,
    user_id integer NOT NULL ,
    PRIMARY KEY(id)
)