CREATE VIEW payment_general_view AS
SELECT DISTINCT
        ON (p.id)
        p.id AS payment_id,
        p.amount as amount,
        p.create_date AS create_date,
        pr.name AS property,
        p.service_type AS service_type,
        p.payment_type AS payment_type,
        p.due_date AS due_date,
        unit_count.total_units AS unit_count
        FROM payment2 p
        LEFT JOIN payment_unit2 pu ON pu.payment_id = p.id
        LEFT JOIN unit u ON u.id = pu.unit_id
        LEFT JOIN building b ON b.id = u.building_id
        LEFT JOIN property pr ON pr.id = b.property_id
        LEFT JOIN (
        SELECT payment_id, COUNT (*) AS total_units
        FROM payment_unit2
        GROUP BY payment_id
        ) AS unit_count ON unit_count.payment_id = p.id;