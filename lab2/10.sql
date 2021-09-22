SELECT id,
CASE 
    WHEN free_cash > 50000 THEN 'Big capital'
    WHEN free_cash > 2000 THEN 'Mid capital'
    WHEN free_cash > 1000 THEN 'Small capital'
    ELSE 'very small capital'
END AS "CapitalPower"
FROM Balance;