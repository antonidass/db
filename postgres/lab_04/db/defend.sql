

CREATE OR REPLACE PROCEDURE update_free_cash(id_ INT, free_cash_ INT)
as $$
ppl = plpy.prepare("UPDATE Balance SET free_cash = free_cash + $1 WHERE id = $2;", ["int", "int"])
plpy.execute(ppl, [free_cash_, id_])
$$ language plpython3u;


SELECT * FROM balance WHERE id = 3;


call update_free_cash(3, 50000);







