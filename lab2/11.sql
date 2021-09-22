CREATE GLOBAL TEMPORARY TABLE BestIncomes (
    id INTEGER NOT NULL,
    income REAL,
    free_cash INTEGER,
    turnover INTEGER
    ) ON COMMIT DELETE ROWS;


INSERT INTO BestIncomes (
SELECT  id,
        income,
        free_cash,
        turnover
FROM Balance
WHERE income > 80
);

SELECT * FROM BestIncomes;
