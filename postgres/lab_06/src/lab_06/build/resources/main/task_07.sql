-- CREATE OR REPLACE PROCEDURE UpdateFreeCash(id_in INT, increase_cash INT)
-- AS '
-- BEGIN
-- UPDATE Balance
-- SET free_cash = free_cash + increase_cash
-- WHERE id = id_in;
-- END;
-- ' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_process_delete() RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM balance
        WHERE OLD.balance_id = balance.id;
        RETURN old;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS process_delete ON company;
CREATE TRIGGER process_delete
    AFTER DELETE ON company
    FOR EACH ROW
EXECUTE PROCEDURE fn_process_delete();
