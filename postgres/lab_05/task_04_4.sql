-- 4. Изменить XML/JSON документ


UPDATE city
SET doc = doc || '{"id":3}'::jsonb
WHERE (doc->'id')::INT = 1;

SELECT * FROM city;
