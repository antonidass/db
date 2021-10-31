SELECT owner_name,
CASE 
WHEN sex = 'M' THEN 'Man'
ELSE 'Female'
END FullNameSex,
CASE 
WHEN country = 'Russia' THEN 'Our country'
ELSE 'Abroad'
END CountryBelonging

FROM CompanyOwner;
