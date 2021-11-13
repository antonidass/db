-- 2. Извлечь значения конкретных узлов или атрибутов XML/JSON документа

CREATE TABLE IF NOT EXISTS city(doc jsonb);
INSERT INTO city VALUES ('{"id":2, "name_city": {"eng_name":"Moscow", "alban_name":"moske", "ukrain_name":"miscva"}}');
INSERT INTO city VALUES ('{"id":2, "name_city": {"eng_name":"Kazan", "alban_name":"Kazan", "ukrain_name":"kizan"}}');

SELECT * FROM city;

SELECT doc->'id' id, doc->'name_city'->'ukrain_name' AS ukrain_name
FROM city;