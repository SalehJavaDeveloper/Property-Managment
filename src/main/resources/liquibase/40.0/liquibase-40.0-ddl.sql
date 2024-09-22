CREATE table special_permission(
                                   id int8 generated always as IDENTITY,
                                   special_permission varchar(50),
                                   permission_id int4,
                                   active_Permission boolean,
                                   CONSTRAINT pm_role
                               FOREIGN KEY(permission_id)
                               References user_granted_roles(id),
                               PRIMARY KEY(id)
)