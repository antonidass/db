BEGIN 
    UpdateFreeCash(1000, 100);
END;
/


SELECT * FROM Balance
WHERE id = 1000;