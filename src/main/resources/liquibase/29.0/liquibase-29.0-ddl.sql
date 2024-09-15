CREATE TABLE packet(
                       id int8 generated always as IDENTITY,
                       company_id int4,
                       CONSTRAINT pm_company
                           FOREIGN KEY(company_id)
                            REFERENCES company(id),
                       PRIMARY KEY(id)
)