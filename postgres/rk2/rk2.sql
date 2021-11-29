-- Криков Антон ИУ7-53Б Вариант 3

drop database if exists rk2;
create database rk2;
\c rk2;


CREATE TABLE Cafedra(
    id integer primary key ,
    name_cafedra varchar(30),
    description varchar(30)
);


CREATE TABLE Teachers (
    id integer primary key ,
    fio varchar(30),
    degree varchar(30),
    position varchar(30),
    cafedra_id integer,
    FOREIGN KEY (cafedra_id) REFERENCES Cafedra(id)
);


CREATE TABLE Subject(
    id integer primary key ,
    name_subject varchar(30),
    hours integer,
    semester integer,
    rating integer
);


CREATE TABLE TeachersSubjects (
    id_teacher integer,
    FOREIGN KEY (id_teacher) REFERENCES Teachers(id),
        id_subject integer,
    FOREIGN KEY (id_subject) REFERENCES Subject(id)
);

INSERT INTO Cafedra
VALUES
(1, 'IU7', 'ICS most important cafedra'),
(2, 'IU3', 'cafedra about AI'),
(3, 'IU9', 'cafedra about Maths');


INSERT INTO Teachers
VALUES
(1, 'Teacher1', 'Docent', 'Junior', 1),
(2, 'Teacher2', 'Professor', 'Junior', 2),
(3, 'Teacher3', 'Docent', 'Mid', 3),
(4, 'Teacher4', 'Candidat', 'Junior', 1),
(5, 'Teacher5', 'Candidat', 'Senior', 2),
(6, 'Teacher6', 'Docent', 'Junior', 2),
(7, 'Teacher7', 'Professor', 'Junior', 3),
(8, 'Teacher8', 'Docent', 'Junior', 3),
(9, 'Teacher9', 'Laborant', 'Mid', 3),
(10, 'Teacher10', 'Docent', 'Senior', 1);

INSERT INTO Subject
VALUES
(1, 'Math', 72, 2, 20),
(2, 'C', 144, 2, 30),
(3, 'C++', 72, 3, 10),
(4, 'Python3', 144, 4, 10),
(5, 'Linear', 218, 1, 14),
(6, 'Differencial', 218, 2, 52),
(7, 'Web', 72, 3, 75),
(8, 'Networks', 144, 6, 32),
(9, 'OS', 72, 4, 1),
(10, 'DB', 218, 3, 100);


INSERT INTO TeachersSubjects
VALUES
    (1, 10),
    (2, 9),
    (3, 8),
    (4, 7),
    (5, 6),
    (6, 5),
    (7, 4),
    (8, 3),
    (9, 2),
    (10, 1);



-- 1) SELECT Использующая предикат сравнения с квантором
-- Вывести предметы у которых рейтинг больше чем у предметов 2 семестра
SELECT name_subject, rating
FROM Subject
WHERE rating > all (SELECT rating FROM Subject WHERE semester = 2);

-- 2) SELECT использующая агрегатные функции в выражениях столбцов
SELECT AVG(hours) AS "Average",
        SUM(hours) / count(*) AS "Calc average"
FROM Subject;

-- 3) Создание новой временной локальной таблицы из рузультирующего набора данных инструкции SELECT
-- Таблица со всеми преподавателями кафедры ИУ9
CREATE TABLE IF NOT EXISTS IU9_Teachers AS
SELECT Teachers.fio, Cafedra.name_cafedra FROM Teachers JOIN Cafedra on Cafedra.id = Teachers.cafedra_id WHERE Cafedra.name_cafedra = 'IU9';

SELECT * FROM IU9_Teachers;


-- №3 Создать хранимую процедуру с входным параметром - имя таблицы
-- которая удаляет дубликаты записей из указанной таблицы в текущей базе данных
-- Соозданную хранимую процедуру протестировать
CREATE TABLE IF NOT EXISTS duplicates (
    id INTEGER,
    name_d varchar(30)
);

CREATE OR REPLACE PROCEDURE remove_duplicates(in_table_name varchar)
AS '
    BEGIN
    EXECUTE ''
        CREATE TABLE temp AS SELECT DISTINCT * FROM '' || in_table_name;
    EXECUTE ''
        DROP TABLE ''  || in_table_name;
    EXECUTE ''
    ALTER TABLE temp RENAME TO '' || in_table_name;
END;
' LANGUAGE plpgsql;


INSERT INTO duplicates
VALUES
(1, 'aa'),
       (1, 'bb'),
        (1, 'bb'),
       (2, 'bb'),
       (2, 'aa'),
       (3, 'aa'),
       (1, 'aa');

CALL remove_duplicates('duplicates');

SELECT * FROM duplicates;

