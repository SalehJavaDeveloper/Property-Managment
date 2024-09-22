CREATE TABLE token(
    id int8 GENERATED ALWAYS AS IDENTITY,
    token varchar(300) NOT NULL,
    token_type varchar(20) NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    user_id integer NOT NULL,
    refresh_token varchar(300) NOT NULL,
    PRIMARY KEY(id)
)