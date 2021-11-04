-- 5. Разделить XML/JSON документ на несколько строк по узлам

CREATE TABLE IF NOT EXISTS drinks(doc json);

INSERT INTO drinks VALUES ('[
                          {"people_id":0, "drink_id": 1},
                          {"people_id":1, "drink_id": 4},
                          {"people_id":2, "drink_id": 1}
]');

SELECT * FROM drinks;

SELECT jsonb_array_elements(doc::jsonb)
FROM drinks;