\c dbcourse

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS balance_logs;
CREATE TABLE balance_logs(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
    , filename TEXT NOT NULL
    , contents TEXT
);


DROP TABLE IF EXISTS balance_8;
CREATE TABLE balance_8(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
    , free_cash int
    , income numeric
  , turnover int
);