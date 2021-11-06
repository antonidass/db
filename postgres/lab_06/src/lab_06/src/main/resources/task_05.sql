CREATE OR REPLACE FUNCTION SumFreeCash (sector_name IN VARCHAR)
RETURNS INT AS '
    SELECT SUM(free_cash)
    FROM Company, Balance
    WHERE sector = sector_name AND Company.balance_id = Balance.id;'
    LANGUAGE sql;