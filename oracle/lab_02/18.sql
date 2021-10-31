UPDATE Balance
SET income = 0
WHERE free_cash < 200;

SELECT * FROM Balance;