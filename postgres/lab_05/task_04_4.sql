-- 4. Изменить XML/JSON документ


UPDATE city
SET doc = doc || '{"id":3}'::jsonb
WHERE (doc->'id')::INT = 2;

SELECT * FROM city;
