DROP TYPE TreeTable;
DROP TYPE TreeRow;

CREATE OR REPLACE TYPE TreeRow IS OBJECT (
    id INTEGER,
    parent_id INTEGER,
    ticker VARCHAR(10),
    country VARCHAR(10)
    );
/

CREATE OR REPLACE TYPE TreeTable IS TABLE OF TreeRow;
/


CREATE OR REPLACE FUNCTION TreeCompany (in_id INTEGER) 
RETURN TreeTable IS tt TreeTable;
BEGIN 
    WITH TempTree(id, parent_id, ticker, country)
    AS (
        SELECT id, parent_id, ticker, country
        FROM CompanyParent
        WHERE id = in_id
        UNION ALL 
        SELECT cp.id, cp.parent_id, cp.ticker, cp.country
        FROM CompanyParent cp
        JOIN TempTree temp_t
        ON cp.parent_id = temp_t.id
        )
        
    SELECT TreeRow(TempTree.id, TempTree.parent_id, TempTree.ticker, TempTree.country)
    BULK COLLECT INTO tt
    FROM TempTree;
    
    RETURN tt;
END;
/