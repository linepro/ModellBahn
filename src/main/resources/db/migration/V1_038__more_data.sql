INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1377, '000010', 822, NULL, 'LokPilot Fx', 481, 2, 6, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1378, '000011', 687, NULL, 'Drehscheibe Decoder', 485, 2, 225, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1379, '00001A', 1318, NULL, 'Weiche Decoder', 485, 2, 182, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1380, '00002D', 842, NULL, 'SwitchPilot', 485, 2, 202, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1381, '00002E', 896, NULL, 'SwitchPilot Servo', 485, 2, 204, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1382, '00002N', 832, NULL, 'LokPilot Fx MTC 1100', 481, 128, 6, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1383, '00000H', 958, NULL, 'Delta Decoder', 480, 14, 1, 'FREI', FALSE);
INSERT INTO decoder (id, decoder_id, decoder_typ_id, artikel_id, bezeichnung, protokoll_id, fahrstufe, adress, status, deleted)
VALUES (1384, '00002K', 782, NULL, 'LokSound M4 MTC', 482, 128, 6, 'FREI', FALSE);
ALTER TABLE decoder ALTER COLUMN id RESTART WITH 1385;

INSERT INTO decoder_cv(decoder_id, cv_id, wert, deleted)
SELECT d.id, c.id, c.werkseinstellung, FALSE
FROM   decoder d,
       decoder_typ_cv c
WHERE  d.decoder_id IN ('000010', '000011', '00001A', '00002D', '00002E', '00002N', '00000H', '00002K')
AND    d.decoder_typ_id = c.decoder_typ_id;

INSERT INTO decoder_funktion(decoder_id, funktion_id, bezeichnung, deleted)
SELECT d.id, f.id, f.funktion, FALSE
FROM   decoder d,
       decoder_typ_funktion f
WHERE  d.decoder_id IN ('000010', '000011', '00001A', '00002D', '00002E', '00002N', '00000H', '00002K')
AND    d.decoder_typ_id = f.decoder_typ_id;

INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1382, 'BR-111', 229, 'BR 111 Lufthansa Airport-Express', 72, 597, 'Henschel & Sohn', DATE '1975-01-01', 227, '111 049-3', 32, 35, 274, 3720, 83.00, 160, 1675.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'static/data/vorbild/BR111/abbildung.jpg', FALSE);
INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1377, 'RLMMSO-56', 289, 'Rungenwagen', 72, 675, NULL, DATE '1958-01-01', 14200, '458 131', NULL, 29, NULL, NULL, 27.00, 100, 13860.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'static/data/vorbild/RLMMSO56/abbildung.jpg', FALSE);
INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1378, 'DHG-700C', 255, 'DHG 700C', 135, 596, 'Henschel', DATE '1973-01-01', 55, '6506', 31, 22, NULL, 507, 54.00, 60, 9840.00, NULL, 1250, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Henschel Henschel 12V1516A', 'HYDRAULISH', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'static/data/vorbild/DHG700C/abbildung.jpg', FALSE);
INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1379, 'BR-86', 246, 'BR 86', 72, 595, 'Henschel', DATE '1934-01-01', 775, '86 107', 30, 4, NULL, 758, 88.50, 80, 13820.00, DATE '1974-01-01', 1400, 850, 850, 2, 570, 660, 14, 2.39, 47.00, 9.00, 117.39, 570, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'static/data/vorbild/BR86/abbildung.jpg', FALSE);
INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1380, 'AVMZ-206', 219, 'Avmz 206 Lufthansa Airport-Express', 72, 636, 'LHB', DATE '1977-01-01', 100, '60 80 84-95 596-2', NULL, 32, NULL, NULL, 45.00, 200, 26400.00, DATE '1996-01-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 54, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'static/data/vorbild/AVMZ206/abbildung.jpg', FALSE);
INSERT INTO vorbild(id, name, gattung_id, bezeichnung, bahnverwaltung_id, unter_kategorie_id, hersteller, bauzeit, menge, betreibsnummer, antrieb_id, achsfolg_id, anfahrzugkraft, leistung, dienstgewicht, geschwindigkeit, lange, ausserdienst, dm_treibrad, dm_laufrad_vorn, dm_laufrad_hinten, zylinder, dm_zylinder, kolbenhub, kesseluberdruck, rostflache, uberhitzerflache, wasservorrat, verdampfung, fahrmotoren, motorbauart, leistungsubertragung, reichweite, kapazitat, klasse, sitzplatze_kl1, sitzplatze_kl2, sitzplatze_kl3, sitzplatze_kl4, aufbau, triebkopf, mittelwagen, drehgestell_bauart, abbildung, deleted)
VALUES(1381, 'BR-601', 241, 'TEE „Mediolanum“', 72, 615, 'Maschinenfabrik Augsburg - Nürnberg AG', DATE '1957-01-01', 19, '601 001-1', 31, 15, 232, 2200, 147.00, 140, 72800.00, DATE '1987-01-01', 950, 900, 900, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2 x Maybach MD650', 'HYDRAULISH', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 2, NULL, 'static/data/vorbild/BR601/abbildung.jpg', FALSE);
ALTER TABLE vorbild ALTER COLUMN id RESTART WITH 1383;

INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1419, 348, '2667-01', 'BR 111 Lufthansa Airport-Express', 597, 19.1, 412, 497, 198, 72, 229, 1382, '111 049-3', DATE '1975-01-01', 35, 488, 34, 389, 379, 507, 465, 'BR 111 049', 'static/data/produkt/MARKLIN/2667-01/anleitungen.pdf', 'static/data/produkt/MARKLIN/2667-01/explosionszeichnungen.pdf', 'static/data/produkt/MARKLIN/2667-01/abbildung.jpg', 'static/data/produkt/MARKLIN/2667-01/grossansicht.jpg', FALSE);
INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1420, 348, '26578-05', 'Rungenwagen', 675, 15.7, 412, 497, 196, 72, 289, 1377, '458 131', DATE '1958-01-01', 29, 491, 34, NULL, 379, NULL, NULL, NULL, NULL, NULL, 'static/data/produkt/MARKLIN/26578-05/abbildung.jpg', 'static/data/produkt/MARKLIN/26578-05/grossansicht.jpg', FALSE);
INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1421, 348, '29147', 'DHG 700C', 596, 11.2, 412, 497, 197, 135, 255, 1378, '6506', DATE '1977-01-01', 22, NULL, 34, 395, 377, 507, 465, '6506', NULL, NULL, 'static/data/produkt/MARKLIN/29147/abbildung.jpg', 'static/data/produkt/MARKLIN/29147/grossansicht.jpg', FALSE);
INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1422, 348, '29533-01', 'BR 86', 595, 15.8, 412, 497, 196, 72, 246, 1379, '86 107', DATE '1933-01-01', 4, NULL, 34, 389, 377, 507, 475, 'BR 86 107', 'static/data/produkt/MARKLIN/29533/anleitungen.pdf', 'static/data/produkt/MARKLIN/29533/explosionszeichnungen.pdf', 'static/data/produkt/MARKLIN/29533-01/abbildung.jpg', 'static/data/produkt/MARKLIN/29533-01/grossansicht.jpg', FALSE);
INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1423, 348, '2667-02', 'Avmz 206 Lufthansa Airport-Express', 635, 26.4, 412, 497, 198, 72, 219, 1380, '60 80 84-95 596-2', DATE '1977-01-01', 32, 488, 34, NULL, 379, NULL, NULL, NULL, NULL, NULL, 'static/data/produkt/MARKLIN/2667-02/abbildung.jpg', 'static/data/produkt/MARKLIN/2667-02/grossansicht.jpg', FALSE);
INSERT INTO produkt (id, hersteller_id, bestell_nr, bezeichnung, unter_kategorie_id, lange, massstab_id, spurweite_id, epoch_id, bahnverwaltung_id, gattung_id, vorbild_id, betreibsnummer, bauzeit, achsfolg_id, sondermodell_id, aufbau_id, licht_id, kupplung_id, steuerung_id, motor_typ_id, anmerkung, anleitungen, explosionszeichnung, abbildung, grossansicht, deleted)
VALUES (1424, 348, '37607', 'TEE „Mediolanum“', 615, 88, 412, 497, 197, 72, 241, 1381, '601 001', DATE '1957-01-01', 15, 491, 36, 395, 379, 507, 465, 'BR 601 001', 'static/data/produkt/MARKLIN/37607/anleitungen.pdf', 'static/data/produkt/MARKLIN/37607/explosionszeichnungen.pdf', 'static/data/produkt/MARKLIN/37607/abbildung.jpg', 'static/data/produkt/MARKLIN/37607/grossansicht.jpg', FALSE);
ALTER TABLE produkt ALTER COLUMN id RESTART WITH 1425;

INSERT INTO produkt_decoder_typ (id, produkt_id, decoder_typ_id, deleted)
VALUES (1, 1419, 946, FALSE);
INSERT INTO produkt_decoder_typ (id, produkt_id, decoder_typ_id, deleted)
VALUES (2, 1421, 970, FALSE);
INSERT INTO produkt_decoder_typ (id, produkt_id, decoder_typ_id, deleted)
VALUES (3, 1422, 1049, FALSE);
INSERT INTO produkt_decoder_typ (id, produkt_id, decoder_typ_id, deleted)
VALUES (4, 1424, 1170, FALSE);
ALTER TABLE person ALTER COLUMN id RESTART WITH 5;

INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1422, '000010', 1419, 'Lufthansa Airport-Express', 389, 379, 507, 465, DATE '2014-03-31', 'EUR', 90, 1, 1, 'BR 111 049', NULL, 'GEKAUFT', NULL, NULL, FALSE);
INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1423, '00005B', 1420, '26578 Set', NULL, 379, NULL, NULL, DATE '2012-12-01', NULL, NULL, 1, 1, NULL, 'Stroh', 'GEKAUFT', NULL, NULL, FALSE);
INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1424, '00000L', 1421, 'DHG 700C', 395, 377, 507, 465, DATE '2006-08-21', NULL, NULL, 1, 1, '6506', NULL, 'GEKAUFT', NULL, NULL, FALSE);
INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1425, '00000E', 1422, 'BR 86', 389, 377, 507, 475, DATE '2007-08-21', 'EUR', 75, 1, 1, 'BR 86 107', NULL, 'GEKAUFT', NULL, NULL, FALSE);
INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1426, '00005K', 1423, 'Lufthansa Airport-Express', 398, 379, NULL, NULL, DATE '2014-03-31', 'EUR', 20, 1, 1, NULL, NULL, 'GEKAUFT', NULL, NULL, FALSE);
INSERT INTO artikel (id, artikel_id, produkt_id, bezeichnung, licht_id, kupplung_id, steuerung_id, motor_typ_id, kaufdatum, wahrung, preis, menge, verbleibende, anmerkung, beladung, status, abbildung, grossansicht, deleted)
VALUES (1427, '00000V', 1424, '„Mediolanum“', 395, 379, 507, 465, DATE '2012-02-04', 'EUR', 556.2, 1, 1, 'BR 601 001', NULL, 'GEKAUFT', NULL, NULL, FALSE);
ALTER TABLE artikel ALTER COLUMN id RESTART WITH 1428;
