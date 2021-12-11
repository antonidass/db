CREATE LANGUAGE plpython3u;

--1
--Определяемую пользователем скалярную функцию CLR
--определяем прибыль по id

CREATE OR REPLACE FUNCTION get_balance_info_by_id(id_ INT) returns varchar
as $$
ppl = plpy.execute("SELECT * FROM Balance")
for balance_row in ppl:
	if balance_row['id'] == id_:
		return balance_row['income']
return 'None'
$$ language plpython3u;

select get_balance_info_by_id(1);