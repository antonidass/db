SELECT owner_name,
        AVG(age),
        AVG(free_cash),
        MIN(free_cash)
FROM CompanyOwner JOIN Balance ON CompanyOwner.balance_id = Balance.id
GROUP BY owner_name;
