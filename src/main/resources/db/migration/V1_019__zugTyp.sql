INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Gütterzug', 'GUTTERZUG', 1418);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Nahvekerszug', 'NAHVEKERS', 1419);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Bahndienstzug', 'BAHNDIENST', 1420);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Interregiozug', 'IR', 1421);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Intercityzug', 'IC', 1422);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'TEE Zug', 'TEE', 1423);
INSERT INTO zug_typ (deleted, bezeichnung, name, id) VALUES (FALSE, 'Militär Zug', 'MILITAR', 1424);
ALTER TABLE zug_typ ALTER COLUMN id RESTART WITH 1425;