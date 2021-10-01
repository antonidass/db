SELECT owner_name, age, sex
FROM CompanyOwner
WHERE balance_id IN (SELECT id
                    FROM Balance
                    WHERE turnover > (SELECT AVG(turnover)
                                      FROM Balance));