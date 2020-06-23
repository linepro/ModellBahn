INSERT INTO steuerung (deleted, bezeichnung, name, id) VALUES (FALSE, 'digital', 'DIGITAL', 507);
INSERT INTO steuerung (deleted, bezeichnung, name, id) VALUES (FALSE, 'Fahrrichtungsumschalter', 'FRU', 508);
INSERT INTO steuerung (deleted, bezeichnung, name, id) VALUES (FALSE, 'Umschaltelektronik', 'USE', 509);
ALTER TABLE steuerung ALTER COLUMN id RESTART WITH 510;