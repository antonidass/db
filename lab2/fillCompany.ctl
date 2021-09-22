LOAD DATA
INFILE 'C:\Users\vpkri\Desktop\db\lab1\generator\company.csv'
INSERT INTO TABLE Company
FIELDS TERMINATED BY  "," OPTIONALLY ENCLOSED BY '"'
(
    ticker,
    company_name,
    foundation_date,
    sector,
    country,
    balance_id,
    shadow
)