--6
--Определяемый пользователем тип данных CLR.

drop type company_small_desc;

create type company_small_desc as (
  name_c text,
  description text,
  sector text
);

































drop function get_name_price_py(name_c varchar, desc_c varchar, sector text);



CREATE OR REPLACE function get_name_price_py(name_c varchar, desc_c varchar, sector text)
RETURNS SETOF company_small_desc
AS $$
    return ([name_c, desc_c, sector],)
$$ LANGUAGE plpython3u;


SELECT * FROM get_name_price_py('Lalala', 'Topalalala', 'alldkfjdfsjfsd');


