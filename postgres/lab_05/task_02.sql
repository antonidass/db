-- 2. Выполнить загрузку и сохранение JSON файла в таблицу.
-- Созданная таблица после всех манипуляций должна соответствовать таблице
-- базы данных, созданной в первой лабораторной работе.

DROP TABLE IF EXISTS Balance_copy;
DROP TABLE IF EXISTS balance_import;

CREATE TABLE IF NOT EXISTS Balance_copy(
                        id_c INTEGER NOT NULL PRIMARY KEY ,
                        free_cash_c INTEGER,
                        stocks_amount_c INTEGER,
                        turnover_c INTEGER,
                        total_assets_c INTEGER,
                        income_c REAL
);

CREATE TABLE IF NOT EXISTS balance_import(doc json);

-- \COPY balance_import FROM '/Users/akrik/Desktop/db/db/postgres/lab_05/data/balance.json';

SELECT * FROM balance_import;

INSERT INTO Balance_copy (id_c, free_cash_c, stocks_amount_c, turnover_c, total_assets_c, income_c)
SELECT (doc->>'id')::INTEGER, (doc->>'free_cash')::INTEGER, (doc->>'stocks_amount')::INTEGER, (doc->>'turnover')::INTEGER, (doc->>'total_assets')::INTEGER, (doc->>'income')::REAL
FROM  balance_import;

SELECT * FROM Balance_copy;