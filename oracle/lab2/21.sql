DELETE FROM Company
WHERE balance_id IN (SELECT id
            FROM Balance
            WHERE income < 10);