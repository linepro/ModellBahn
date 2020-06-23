INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrgestell der Lok aus Metall', 'LK', NULL, 34);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrgestell und vorwiegender Aufbau der Loks aus Metall', 'LMK', NULL, 35);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrgestell und Aufbau der Lokomotive aus Metall', 'LM', NULL, 36);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrgestell des Wagens aus Metall', 'WK', NULL, 37);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Überwiegender Teil des Wagenaufbaus aus Metall', 'WMK', NULL, 38);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrgestell und Aufbau des Wagens aus Metall', 'WM', NULL, 39);
INSERT INTO aufbau (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Überwiegender Teil des Lokaufbaues aus Metall', 'LKM', NULL, 40);
ALTER TABLE aufbau ALTER COLUMN id RESTART WITH 41;