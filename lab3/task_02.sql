DROP TYPE OwnerShortTable;
DROP TYPE OwnerShortRow;

CREATE OR REPLACE TYPE OwnerShortRow IS OBJECT (
    ow_name VARCHAR(10), 
    age INTEGER,
    turnover INTEGER
    );
/

CREATE OR REPLACE TYPE OwnerShortTable IS TABLE OF OwnerShortRow;
/

CREATE OR REPLACE FUNCTION ShortInfo (id_owner INTEGER)
RETURN OwnerShortTable AS ost OwnerShortTable;
BEGIN
    SELECT OwnerShortRow(owner_name,
                        age,
                        turnover)
    BULK COLLECT INTO ost
    FROM CompanyOwner, Balance
    WHERE CompanyOwner.id = id_owner AND Balance.id = CompanyOwner.balance_id;
RETURN ost;
END;
/