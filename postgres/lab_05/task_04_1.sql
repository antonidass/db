-- 4. Выполнить следующие действия:
-- 1. Извлечь XML/JSON фрагмент из XML/JSON документа


CREATE TABLE IF NOT EXISTS balance_id_income (
    id INT ,
    income REAL
);


SELECT * FROM balance_id_income;

SELECT * FROM balance_import, json_populate_record(null::balance_id_income, doc);

SELECT id, income
FROM balance_import, json_populate_record(null::balance_id_income, doc)
WHERE income > 80;






SELECT * FROM balance_import;

SELECT doc->>'id' Id,  doc->>'income' Income
FROM balance_import;

