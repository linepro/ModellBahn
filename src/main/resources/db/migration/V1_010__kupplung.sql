INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Relex Kupplung', 'RELEX', 'static/kupplung/RELEX/abbildung.png', 377);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Märklin-Kurzkupplungen mit Drehpunkt', 'KK', 'static/kupplung/KK/abbildung.png', 378);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Märklin-Kurzkupplungen in Norm-Aufnahme mit Drehpunkt', 'NEM', 'static/kupplung/NEM/abbildung.png', 379);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Märklin-Kurzkupplungen in Norm-Aufnahme mit Kulissenführung', 'NEMKK', 'static/kupplung/NEMKK/abbildung.png', 380);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Märklin-Kurzkupplungen in Norm-Aufnahme mit Stromfürhrender Kulissenführung', 'SFKK', 'static/kupplung/SFKK/abbildung.png', 381);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Telex Kupplung', 'TELEX', 'static/kupplung/TELEX/abbildung.png', 382);
INSERT INTO kupplung (deleted, bezeichnung, name, abbildung, id) VALUES (FALSE, 'Kupplung', 'EINFACH', 'static/kupplung/EINFACH/abbildung.png', 383);
ALTER TABLE kupplung ALTER COLUMN id RESTART WITH 384;