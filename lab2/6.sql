SELECT owner_name, age, sex FROM CompanyOwner
WHERE age > ANY (SELECT age FROM CompanyOwner 
                 WHERE owner_name = 'Alina');