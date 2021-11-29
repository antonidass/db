--4
--Хранимую процедуру CLR
--добавляет информацию о балансе

create or replace procedure add_balance(free_cash int, stocks_amount int , total_assets int, turnover int , income real) as
$$
statement = plpy.prepare("INSERT INTO Balance(free_cash, stocks_amount, turnover, total_assets, income) VALUES($1, $2, $3, $4, $5);", ["int", "int", "int", "int", "real"])
plpy.execute(statement, [free_cash, stocks_amount,  turnover,  total_assets, income])
$$ language plpython3u;

call add_balance(230, 20, 30, 552, 32.1);