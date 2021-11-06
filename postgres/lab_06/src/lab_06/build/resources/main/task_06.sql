DROP FUNCTION OwnerShortRow(id_in INT);

CREATE OR REPLACE FUNCTION OwnerShortRow(id_in INT)
RETURNS TABLE (
    ow_name VARCHAR,
    age INT,
    turnover INT
    )
AS '
BEGIN
    RETURN QUERY
    SELECT owner_name, companyowner.age, Balance.turnover
    FROM companyowner JOIN balance ON balance.id = CompanyOwner.balance_id
    WHERE id_in = companyowner.id;
END;
'LANGUAGE plpgsql;