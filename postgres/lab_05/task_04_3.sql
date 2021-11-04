-- 3. Выполнить проверку существования узла или атрибута

CREATE OR REPLACE FUNCTION check_city(check_id jsonb)
RETURNS VARCHAR AS '
    SELECT CASE
                WHEN count.cnt > 0
                THEN ''true''
                ELSE ''false''
                END AS comment
    FROM (
            SELECT COUNT(doc->''id'') cnt
            FROM city
            WHERE doc -> ''id'' @> check_id
    ) as count;
' LANGUAGE sql;


SELECT * FROM city;

SELECT check_city('0');