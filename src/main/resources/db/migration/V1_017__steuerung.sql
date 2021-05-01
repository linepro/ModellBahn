INSERT INTO steuerung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'digital', 'DIGITAL', 'static/steuerung/DIGITAL/abbildung.png', 507);
INSERT INTO steuerung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Fahrrichtungsumschalter', 'FRU', 'static/steuerung/FRU/abbildung.png', 508);
INSERT INTO steuerung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Umschaltelektronik', 'USE', 'static/steuerung/USE/abbildung.png', 509);
ALTER TABLE steuerung ALTER COLUMN id RESTART WITH 510;