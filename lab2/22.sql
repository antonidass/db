WITH StockPeoples(group_name, avg_years) AS (
SELECT owner_name, AVG(age)
FROM CompanyOwner
GROUP BY owner_name
)
SELECT * FROM StockPeoples;