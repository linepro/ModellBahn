INSERT INTO antrieb (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Akku', 'AKKU', 'static/data/antrieb/AKKU/abbildung.png', 29);
INSERT INTO antrieb (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Dampf', 'DAMPF', 'static/data/antrieb/DAMPF/abbildung.png', 30);
INSERT INTO antrieb (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Diesel', 'DIESEL', 'static/data/antrieb/DIESEL/abbildung.png', 31);
INSERT INTO antrieb (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Elektro', 'ELEKTRO', 'static/data/antrieb/ELEKTRO/abbildung.png', 32);
INSERT INTO antrieb (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Druckluft', 'DRUCKLUFT', 'static/data/antrieb/DRUCKLUFT/abbildung.png', 33);
ALTER TABLE antrieb ALTER COLUMN id RESTART WITH 34;