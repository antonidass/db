DROP TYPE AccTable;
DROP TYPE AccRow;


CREATE OR REPLACE TYPE AccRow IS OBJECT (
    id INTEGER
    );
/


CREATE OR REPLACE TYPE AccTable IS TABLE OF AccRow;
/


CREATE OR REPLACE PROCEDURE UpdateFreeCashTree (in_id INTEGER, post_name VARCHAR) IS
    act AccTable := AccTable();
BEGIN
    WITH CompanyTree(id, parent_id)
    AS (
        SELECT id, parent_id
        FROM CompanyParent
        WHERE in_id = id
        UNION ALL 
        SELECT cp.id, cp.parent_id
        FROM CompanyParent cp
        JOIN CompanyTree ct
        ON cp.parent_id = ct.id
    )
    SELECT AccRow(id)
    BULK COLLECT INTO act 
    FROM CompanyTree;
    
    UPDATE CompanyParent 
    SET CompanyParent.company_name = CONCAT(CompanyParent.company_name, post_name)
    WHERE CompanyParent.id IN (SELECT id FROM TABLE(act));
END;
/
    
    
    
    
    
    
    
    
    
    
    