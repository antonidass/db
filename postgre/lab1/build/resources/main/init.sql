CREATE TABLE IF NOT EXISTS Balance(
                        id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        free_cash INTEGER,
                        stocks_amount INTEGER,
                        turnover INTEGER,
                        total_assets INTEGER,
                        income REAL
);

CREATE TABLE IF NOT EXISTS Company(
                        ticker VARCHAR(6) PRIMARY KEY,
                        company_name VARCHAR(30) NOT NULL,
                        foundation_date VARCHAR(20) NOT NULL,
                        sector VARCHAR(22) NOT NULL,
                        country VARCHAR(20) NOT NULL,
                        balance_id INTEGER NOT NULL,
                        FOREIGN KEY (balance_id) REFERENCES Balance(id),
                        shadow CHAR(3) NOT NULL CHECK (shadow IN ('YES', 'NO'))
);

CREATE TABLE IF NOT EXISTS CompanyOwner(
                             id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             owner_name VARCHAR(30) NOT NULL,
                             age INTEGER NOT NULL,
                             date_registration VARCHAR(30) NOT NULL,
                             sex CHAR(1) NOT NULL CHECK (sex IN ('M', 'F')),
                             country VARCHAR(20) NOT NULL,
                             balance_id INTEGER NOT NULL,
                             FOREIGN KEY (balance_id) REFERENCES Balance(id)
);



CREATE TABLE IF NOT EXISTS StockExchange(
                              id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              date_foundation VARCHAR(30) NOT NULL,
                              name_stockexchange VARCHAR(30) NOT NULL,
                              available CHAR(3) NOT NULL CHECK (available IN ('YES', 'NO'))
);

CREATE TABLE IF NOT EXISTS StockMarket(
                            id_market INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            ticker VARCHAR(6) NOT NULL,
                            owner_id INTEGER NOT NULL,
                            stock_exchange INTEGER NOT NULL,
                            FOREIGN KEY (ticker) REFERENCES Company(ticker),
                            FOREIGN KEY (owner_id) REFERENCES CompanyOwner(id),
                            FOREIGN KEY (stock_exchange) REFERENCES StockExchange(id),
                            weekend CHAR(3) NOT NULL CHECK (weekend IN ('YES', 'NO'))
);
