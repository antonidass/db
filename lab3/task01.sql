CREATE OR REPLACE FUNCTION SumFreeCash (sector_name IN VARCHAR)
RETURN INTEGER
IS
    free_cash_sum INTEGER;
BEGIN
    SELECT SUM(free_cash)
    INTO free_cash_sum
    FROM Company, Balance
    WHERE sector = sector_name AND Company.balance_id = Balance.id ;
RETURN free_cash_sum;
END;
    