SELECT ticker, sector, company_name, country, foundation_date,
AVG(free_cash) OVER(PARTITION BY sector, country) AS AvgCash
FROM Company
JOIN Balance ON Company.balance_id = Balance.id;