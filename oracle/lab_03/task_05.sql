CREATE OR REPLACE PROCEDURE UpdateFreeCash(id_in INTEGER, increase_cash INTEGER) AS
BEGIN
    UPDATE Balance
    SET free_cash = free_cash + increase_cash
    WHERE id = id_in;
END;
/