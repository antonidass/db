SELECT sector 
FROM Company JOIN Balance ON Company.balance_id = Balance.id
GROUP BY sector
HAVING AVG(Balance.turnover) > 50000000;