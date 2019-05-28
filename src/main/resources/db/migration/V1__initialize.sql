USE test;
DROP TABLE IF EXISTS parts;

CREATE TABLE parts (
  id                    INT(11) NOT NULL AUTO_INCREMENT,
  name                  VARCHAR(255) NOT NULL,
  amount                INT(11) NOT NULL,
  necessary             TINYINT(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO parts (name, necessary, amount) VALUES
('материнская плата', 1, 3),
('звуковая карта', 0, 2),
('процессор', 1, 2),
('подсветка корпуса', 0, 0),
('HDD диск', 0, 1),
('корпус', 1, 10),
('память', 1, 10),
('SSD диск', 1, 15),
('видеокарта', 0, 7),
('WiFi адаптер', 0, 19),
('Bluetooth адаптер', 0, 6),
('система охлаждения процессора', 0, 9),
('кулер', 0, 15),
('CD дисковод', 0, 5),
('Floppy дисковод', 0, 0),
('источник резервного питания', 1, 15);