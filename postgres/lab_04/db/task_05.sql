--5
--Триггер CLR
--при удалении компании удаляется также ее баланс

create or replace function delete_balance_record()
returns trigger
as $$
query = plpy.prepare("delete from Balance WHERE Balance.id = $1", ["int"])
plpy.execute(query, [TD['old']["balance_id"]])
return TD['new']
$$ language  plpython3u;


drop trigger del_company_trigger on company;


create trigger del_company_trigger
after delete on company for each row
execute procedure delete_balance_record();


DELETE FROM company
WHERE ticker = 'hbu';

