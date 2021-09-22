LOAD DATA
INFILE 'C:\Users\vpkri\Desktop\db\lab1\generator\stock_exchange.csv'
INSERT INTO TABLE StockExchange
FIELDS TERMINATED BY  "," OPTIONALLY ENCLOSED BY '"'
(
    date_foundation,
    name_stockexchange,
    available
)