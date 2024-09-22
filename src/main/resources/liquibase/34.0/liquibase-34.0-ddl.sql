CREATE VIEW payment_detailed_view AS
SELECT p.id                                    AS payment_id,
       t.id                                    AS transaction_id,
       pr.name                                 AS property,
       b.name                                  AS building,
       u.id                                    AS unit,
       p.amount                                AS amount,
       p.description                           AS description,
       t.status                                AS status,
       t.url                                   AS payment_link,
       t.payment_date                          AS payment_date,
       ARRAY_AGG(tn.name || ' ' || tn.surname) AS tenants
FROM payment2 p
         LEFT JOIN payment_unit2 pu ON pu.payment_id = p.id
         LEFT JOIN transaction2 t ON t.payment_unit_id = pu.id
         LEFT JOIN unit u ON u.id = pu.unit_id
         LEFT JOIN building b ON b.id = u.building_id
         LEFT JOIN property pr ON pr.id = b.property_id
         LEFT JOIN tenant tn ON tn.unit_id = u.id
GROUP BY p.id,
         t.id,
         pr.name,
         b.name,
         u.id,
         p.amount,
         p.description,
         t.status,
         t.url,
         t.payment_date;
