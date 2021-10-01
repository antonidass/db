WITH CompanyTree(id, ticker, company_name, country, sector, parent_id, slevel)
AS
(
    SELECT id, ticker, company_name, country, sector, parent_id, 1 AS slevel
    FROM CompanyParent
    WHERE parent_id IS NULL
    UNION ALL 
    SELECT cp.id, cp.ticker, cp.company_name, cp.country, cp.sector, cp.parent_id, ct.slevel + 1 
    FROM CompanyParent cp
    JOIN CompanyTree ct ON cp.parent_id = ct.id
)
SELECT * FROM CompanyTree
WHERE slevel < 10;



--
--
--CREATE TABLE CompanyWithID AS (
--SELECT ROW_NUMBER() OVER(ORDER BY ticker) AS id, ticker, company_name, country, sector
--FROM Company
--);
--
--SELECT * FROM CompanyWithID;



--CREATE TABLE RandomTickersN(
--parent_ticker VARCHAR(6),
--parent_id INTEGER
--);
--
--
--INSERT INTO RandomTickersN
--WITH RandomTickers(parent_ticker, parent_id) AS (
--    SELECT ticker, ROW_NUMBER() OVER(ORDER BY company_name) AS parent_id
--    FROM Company
--    ORDER BY DBMS_RANDOM.VALUE
--    )
--SELECT parent_ticker, parent_id
--FROM RandomTickers;


--CREATE TABLE CompanyParent(
--id INTEGER,
--ticker VARCHAR(6) PRIMARY KEY,
--company_name VARCHAR(100),
--country VARCHAR(100),
--sector VARCHAR(100),
--parent_id INTEGER
--);

--INSERT INTO CompanyParent
--WITH temp(id, ticker, company_name, country, sector, parent_id) AS (
--    SELECT id, ticker, company_name, country, sector, parent_id
--    FROM CompanyWithID JOIN RandomTickersN ON CompanyWithID.ticker = RandomTickersN.parent_ticker
--    )
--    SELECT * FROM temp;
--
--




