--2
--Пользовательскую агрегатную функцию CLR
--количество владельцов с именем name

CREATE OR REPLACE FUNCTION get_count_person_by_name(name varchar) returns varchar
as $$
count = 0
ppl = plpy.execute("SELECT * FROM CompanyOwner")
for owner in ppl:
	if owner['owner_name'] == name:
		count += 1
return count
$$ language plpython3u;

select get_count_person_by_name('Vitalik');