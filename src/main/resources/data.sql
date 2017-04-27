CREATE TABLE if NOT EXISTS Todo(
  id IDENTITY  PRIMARY KEY ,
  done BOOLEAN,
  text VARCHAR (255)
);
DELETE from Todo;
INSERT INTO Todo VALUES (1, true, 'sjskhk');
INSERT INTO Todo VALUES (2, true, 'sdg');
INSERT INTO Todo VALUES (3, FALSE , 'sg');