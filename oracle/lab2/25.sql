WITH OwnerDup(id, owner_name, sex, cnt)
AS (
    SELECT id, owner_name, sex, ROW_NUMBER() OVER (PARTITION BY owner_name, sex ORDER BY id) as cnt 
    FROM CompanyOwner
    )
SELECT * FROM OwnerDup
WHERE cnt = 1;