-- 1. Из таблиц базы данных, созданной в первой
-- лабораторной работе, извлечь данные в JSON.

-- psql -h localhost -p 5432 -U postgres

\COPY
(
    SELECT row_to_json(b) result FROM balance b
) TO '/Users/akrik/Desktop/db/db/postgres/lab_05/data/balance.json';
