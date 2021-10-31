SELECT * FROM TABLE(ShortInfo('1000'));


SELECT owner_name, age, turnover
FROM CompanyOwner, Balance
WHERE CompanyOwner.id = 1000 AND Balance.id = CompanyOwner.balance_id;