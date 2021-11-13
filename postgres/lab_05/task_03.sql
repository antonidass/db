-- 3. Создать таблицу, в которой будет атрибут(-ы) с типом JSON, или
-- добавить атрибут с типом JSON к уже существующей таблице.
-- Заполнить атрибут правдоподобными данными с помощью команд INSERT или UPDATE


CREATE TABLE IF NOT EXISTS ShadowCompanies (
    data json
);

INSERT INTO ShadowCompanies
SELECT * FROM json_object('{company_id, name, sector}', '{3, Apple, Technology}');

SELECT * FROM ShadowCompanies;



