LOAD DATA
INFILE 'C:\Users\vpkri\Desktop\db\lab1\generator\market.csv'
INSERT INTO TABLE StockMarket
FIELDS TERMINATED BY  "," OPTIONALLY ENCLOSED BY '"'
(
    ticker ,
    owner_id ,
    stock_exchange,
    weekend
)