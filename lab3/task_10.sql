DROP VIEW OwnersAndBalance;

CREATE OR REPLACE VIEW OwnersAndBalance AS 
SELECT balance_id, owner_name, age, sex, free_cash, income
FROM CompanyOwner JOIN Balance ON CompanyOwner.balance_id = Balance.id;
/


CREATE OR REPLACE TRIGGER UpdateViewTrigger
INSTEAD OF UPDATE
ON OwnersAndBalance
FOR EACH ROW
BEGIN
    IF :new.income > 80 THEN
        UPDATE Balance
        SET Balance.income = 81
        WHERE id = :new.balance_id;
    END IF;
END;
/