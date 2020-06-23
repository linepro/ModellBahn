INSERT INTO zug (deleted, bezeichnung, name, zug_typ_id, id) VALUES (FALSE, 'TEE „Bavaria“', 'BAVARIA', 1423, 1425);
ALTER TABLE zug ALTER COLUMN id RESTART WITH 1426;