DROP TYPE GoodTable;
DROP TYPE GoodRow;


CREATE OR REPLACE TYPE GoodRow IS OBJECT (
    comp_name VARCHAR(20),
    country VARCHAR(10),
    income REAL, 
    free_cash INTEGER
    );
/
    
CREATE OR REPLACE TYPE GoodTable IS TABLE OF GoodRow;
/


CREATE OR REPLACE FUNCTION GoodInfo (sector_arg VARCHAR)
RETURN GoodTable AS gt GoodTable;
    avg_income REAL := 0;
BEGIN
    SELECT AVG(income)
    INTO avg_income
    FROM Company JOIN Balance ON Company.balance_id = Balance.id;
    
    SELECT GoodRow(company_name,
                   country,
                   income,
                   free_cash)
    BULK COLLECT INTO gt
    FROM (
        SELECT company_name, country, income, free_cash
        FROM Company, Balance
        WHERE Company.balance_id = Balance.id AND sector = sector_arg
        )
    WHERE income > avg_income;
RETURN gt;
END;
/
