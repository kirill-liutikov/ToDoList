CREATE TABLE IF NOT EXISTS test.tasks (
  id          INT NOT NULL AUTO_INCREMENT,
  create_dt   DATETIME,
  description VARCHAR(79),
  hasDone     BIT NULL     DEFAULT 0,
  PRIMARY KEY (`id`));



INSERT INTO test.tasks VALUES (1, 20170426161122, 'Это первый таск', 1);
INSERT INTO test.tasks VALUES (2, 20170429171537, 'Второй таск', 1);
INSERT INTO test.tasks VALUES (3, 20170429182121, 'Стать java developer', 0);
INSERT INTO test.tasks VALUES (4, 20170426194152, 'Разобраться с гитом', 0);
INSERT INTO test.tasks VALUES (5, 20170426194253, 'Стать счастливым', 1);
INSERT INTO test.tasks VALUES (6, 20170501194454, 'Узнать ответ на главный вопрос', 0);
INSERT INTO test.tasks VALUES (7, 20170502194454, 'Завоевать вселенную не привлекая внимания санитаров', 1);
INSERT INTO test.tasks VALUES (8, 20170503194454, 'Изобрести вечный двигатель', 0);