CREATE OR REPLACE PROCEDURE CompanyNames
IS
    cur_name VARCHAR2(64);
    CURSOR comp_cursor IS SELECT * FROM Company;
BEGIN
    DBMS_OUTPUT.enable;
    FOR company IN comp_cursor
    LOOP
        cur_name := company.company_name;
        DBMS_OUTPUT.put_line(cur_name);
    END LOOP;
END;
/