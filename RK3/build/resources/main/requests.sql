DROP DATABASE IF EXISTS rk3;
CREATE DATABASE rk3;
\c rk3;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS records;

CREATE TABLE IF NOT EXISTS employee (
id INT PRIMARY KEY,
fio TEXT,
date_birth DATE,
dep TEXT
);

CREATE TABLE IF NOT EXISTS records (
id INT PRIMARY KEY,
employee_id INT,
date_record DATE,
day_week TEXT,
time_event TIME,
type_event INT,
FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);



INSERT INTO employee (id, fio, date_birth, dep) VALUES
 (1, 'Иванов Иван Иванович', '1990-09-25', 'ИТ'),
 (2, 'Петров Петр Петрович', '1987-11-12', 'Бухгалтерия'),
 (3, 'Криков Антон Владимирович', '2001-03-04', 'ИТ'),
 (4, 'Ляляля Тополя Алексеевич', '2001-11-06', 'Бухгалтерия');

INSERT INTO records (id, employee_id, date_record, day_week, time_event, type_event) VALUES
                                                             (1, 1, '2018-12-14', 'Суббота', '9:00', 1),
                                                             (2, 1, '2018-12-14', 'Суббота', '9:20', 2),
                                                             (3, 1, '2018-12-14', 'Суббота', '9:25', 1),
                                                             (4, 2, '2018-12-14', 'Суббота', '9:05', 1);




