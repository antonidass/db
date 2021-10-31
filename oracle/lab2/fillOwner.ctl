LOAD DATA
INFILE 'C:\Users\vpkri\Desktop\db\lab1\generator\owner.csv'
INSERT INTO TABLE CompanyOwner
FIELDS TERMINATED BY  "," OPTIONALLY ENCLOSED BY '"'
(
    owner_name, 
    age,
    date_registration,
    sex ,
    country,
    balance_id
)