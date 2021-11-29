--6
--Определяемый пользователем тип данных CLR.

drop type company_small_desc;

create type company_small_desc as (
  name_c text,
  description text,
  sector text
);
