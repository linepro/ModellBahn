INSERT INTO anderung (id, deleted, anderung_id, anderungsdatum, anderungs_typ, anmerkung, bezeichnung, stuck, artikel_id) VALUES (1, false, 1, '2014-02-15', 'UMGEBAUT', 'Digitalierung', '', 1, 1417);
ALTER TABLE anderung ALTER COLUMN id RESTART WITH 2;    