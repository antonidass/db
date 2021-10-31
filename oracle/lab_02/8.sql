SELECT 
id,
(SELECT MAX(free_cash) FROM Balance) AS "max_free_cash",
(SELECT AVG(income) FROM Balance) AS "avg_income",
(SELECT SUM(turnover) FROM Balance) AS all_turnover,
(SELECT owner_name 
    FROM CompanyOwner
    WHERE CompanyOwner.balance_id = Balance.id) AS "owner_name"
FROM Balance;


