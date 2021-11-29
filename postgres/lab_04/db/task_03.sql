--3
--Определяемую пользователем табличную функцию CLR
-- выводим все компании из сектора sector
create or replace function get_companies(sector varchar ) returns table(ticker varchar , company_name varchar , foundation_date varchar, sector varchar , country varchar , shadow varchar)
as $$
companies = []
ppl = plpy.execute("select * from Company")
for company in ppl:
	if company['sector'] == sector:
		companies.append(company)
return companies
$$ language plpython3u;

select * from get_companies('Energy');