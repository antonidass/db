SELECT AVG(income) AS "avg_income", SUM(turnover), MAX(income), MIN(income)
FROM Balance; 

SELECT income FROM Balance
WHERE income > (SELECT AVG(income) FROM Balance);
