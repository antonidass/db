LOAD DATA
INFILE 'C:\Users\vpkri\Desktop\db\lab1\generator\balance.csv'
INSERT INTO TABLE Balance
FIELDS TERMINATED BY  "," OPTIONALLY ENCLOSED BY '"'
(
    free_cash ,
    stocks_amount ,
    turnover ,
    total_assets ,
    income
)