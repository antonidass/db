SELECT * FROM Balance
WHERE ID IN (SELECT ID
             FROM CompanyOwner
             WHERE age < 30
             )
      AND income > 80;