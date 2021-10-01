SELECT owner_name, age, sex, balance_id
FROM CompanyOwner
WHERE balance_id IN (   SELECT ID 
                        FROM Balance
                        WHERE income > (SELECT AVG(income) FROM Balance));