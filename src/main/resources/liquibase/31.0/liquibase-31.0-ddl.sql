CREATE TABLE company_roles(
id int8 generated always as IDENTITY,
company_role_name varchar(50),
permissions varchar(50),
company_id int4,
CONSTRAINT pm_company
FOREIGN KEY(company_id)
REFERENCES company(id),
PRIMARY KEY(id)
)