CREATE OR REPLACE PROCEDURE UpdateFreeCash(id_in INT, increase_cash INT)
AS '
BEGIN
UPDATE Balance
SET free_cash = free_cash + increase_cash
WHERE id = id_in;
END;
' LANGUAGE plpgsql;