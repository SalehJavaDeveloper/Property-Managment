CREATE TABLE user_granted_roles(
                                   id int8 generated always as IDENTITY,
                                   user_role varchar(50),
                                   user_id int4,
                                   CONSTRAINT pm_user
                                       FOREIGN KEY(user_id)
                                           REFERENCES users(id),
                                   PRIMARY KEY(id)
)