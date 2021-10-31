SELECT owner_name, age
FROM CompanyOwner
WHERE EXISTS (SELECT * FROM StockMarket 
              WHERE ticker IS NOT NULL);