UPDATE Balance
SET turnover = (SELECT AVG(turnover)   
                FROM Balance
                );
                
SELECT * FROM Balance;


UPDATE Balance
SET turnover = (dbms_random.value(100, 100000000));



