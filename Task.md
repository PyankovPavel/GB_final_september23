### Задание

* ### 1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками,   хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и    ослы), а затем объединить их. Просмотреть содержимое созданного файла.    Переименовать файл, дав ему новое имя (Друзья человека).  
![](imgs/1.png)
![](imgs/1-2.png)
![](imgs/1-3.png)
* ### 2. Создать директорию, переместить файл туда.
![](imgs/2.png)
* ### 3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.  
![](imgs/3-1.png)
![](imgs/3-2.png)
* ### 4. Установить и удалить deb-пакет с помощью dpkg.  
![](imgs/4-1.png)
![](imgs/4-2.png)
* ### 5. Выложить историю команд в терминале ubuntu
![](imgs/5.png)
* ### 6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних   животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные   войдут: Лошади, верблюды и ослы).
![](imgs/6.png)
* ### 7. В подключенном MySQL репозитории создать базу данных “Друзья    человека”
```
CREATE DATABASE HumansFriends;
```
* ### 8. Создать таблицы с иерархией из диаграммы в БД
```
USE HumansFriends;
CREATE TABLE Animal
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20)
);

INSERT INTO Animal (Class_name)
VALUES ('PackAnimals'),
('Pets');  


CREATE TABLE PackAnimals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    PAnimal_type VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES Animal (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO PackAnimals (PAnimal_type, Class_id)
VALUES ('Horses', 1),
('Donkeys', 1),  
('Camels', 1); 
    
CREATE TABLE Pets
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Pets_type VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES Animal (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets (Pets_type, Class_id)
VALUES ('Cats', 2),
('Dogs', 2),  
('Hamsters', 2); 

CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    type_id int,
    Foreign KEY (type_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

* ### 9. Заполнить низкоуровневые таблицы именами(животных), командами    которые они выполняют и датами рождения  
```
USE HumansFriends;

INSERT INTO cats (Name, Birthday, Commands, type_id)
VALUES ('Tom', '2011-01-01', 'eat', 1),
('Jerry', '2016-01-01', 'sleep', 1),  
('Myatych', '2017-01-01', 'run', 1);

INSERT INTO dogs (Name, Birthday, Commands, type_id)
VALUES ('Archi', '2011-04-19', 'bark', 2),
('Uma', '2016-03-08', 'play', 2),  
('Alma', '2017-02-28', 'run', 2);

INSERT INTO hamsters (Name, Birthday, Commands, type_id)
VALUES ('Chip', '2015-03-09', 'eat', 3),
('Dale', '2015-03-09', 'sleep', 3),  
('Gaika', '2013-02-14', 'run', 3);

INSERT INTO horses (Name, Birthday, Commands, type_id)
VALUES ('Bolivar', '2016-06-11', 'run', 1),
('Grusha', '2014-02-08', 'stop', 1),  
('Venik', '2014-03-13', 'eat', 1);

INSERT INTO camels (Name, Birthday, Commands, type_id)
VALUES ('South', '2017-05-12', 'walk', 2),
('Sand', '2015-01-07', 'rest', 2),  
('Lucy', '2013-04-12', 'eat', 2);

INSERT INTO donkeys (Name, Birthday, Commands, type_id)
VALUES ('Ia', '2016-06-11', 'shout', 3),
('Sand', '2014-12-06', 'eat', 3),  
('Lucy', '2014-05-11', 'bite', 3);
```
* ### 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
```
use humansfriends;

TRUNCATE camels;

INSERT INTO horses (`Name`, Birthday, Commands, type_id)
SELECT `Name`, Birthday, Commands, type_id
FROM donkeys;

DROP TABLE donkeys;

RENAME TABLE horses TO horses_and_donkeys;
```
* ### 11. Создать новую таблицу “молодые животные” в которую попадут все    животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
```
use humansfriends;

CREATE TABLE animals_temp AS 
SELECT * FROM horses_and_donkeys
UNION SELECT * FROM dogs
UNION SELECT * FROM cats
UNION SELECT * FROM hamsters;

CREATE TABLE young_animals AS
SELECT `Name`, Birthday, Commands, type_id, TIMESTAMPDIFF(YEAR, Birthday, CURDATE()) AS Age
FROM animals_temp WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -9 YEAR) AND ADDDATE(CURDATE(), INTERVAL -2 YEAR);
/* Чтобы не переделывать таблицы сделал интервал запроса от 2 до 9 лет */
 
SELECT * FROM young_animals;
```
### Результат:
![](imgs/11.png)

* ### 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.
```
use humansfriends;

SELECT hd.`Name`, hd.Birthday, hd.Commands, pa.PAnimal_type, ya.Age
FROM horses_and_donkeys hd
LEFT JOIN young_animals ya ON ya.`Name` = hd.`Name`
LEFT JOIN packanimals pa ON pa.Id = hd.type_id
UNION
SELECT c.Name, c.Birthday, c.Commands, pe.Pets_type, ya.Age
FROM cats c
LEFT JOIN young_animals ya ON ya.`Name` = c.`Name`
LEFT JOIN pets pe ON pe.Id = c.type_id
UNION
SELECT d.Name, d.Birthday, d.Commands, pe.Pets_type, ya.Age
FROM dogs d
LEFT JOIN young_animals ya ON ya.`Name` = d.`Name`
LEFT JOIN pets pe ON pe.Id = d.type_id
UNION
SELECT hm.Name, hm.Birthday, hm.Commands, pe.Pets_type, ya.Age 
FROM hamsters hm
LEFT JOIN young_animals ya ON ya.Name = hm.Name
LEFT JOIN pets pe ON pe.Id = hm.type_id;
```
### Результат:
![](imgs/12.png)
13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.  
14. Написать программу, имитирующую работу реестра домашних животных. <br>
    В программе должен быть реализован следующий функционал: <br>
    14.1 Завести новое животное <br>
    14.2 определять животное в правильный класс <br>
    14.3 увидеть список команд, которое выполняет животное <br>
    14.4 обучить животное новым командам <br>
    14.5 Реализовать навигацию по меню <br>
15. Создайте класс счетчик, у которого есть метод add(), увеличивающий
    значение внутренней int переменной на 1 при нажатии “Завести новое
    животное” Сделайте так, чтобы с объектом такого типа можно было работать в
    блоке try-with-resources. Нужно бросить исключение, если работа с объектом
    типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
    считать в ресурсе try, если при заведении животного заполнены все поля.
