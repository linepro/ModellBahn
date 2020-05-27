--<ScriptOptions statementTerminator=";"/>

CREATE TABLE IF NOT EXISTS ACHSFOLG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS ANDERUNG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ANDERUNG_ID INTEGER NOT NULL,
		ANDERUNGSDATUM DATE,
		ANDERUNGS_TYP VARCHAR(255) NOT NULL,
		ANMERKUNG VARCHAR(255),
		BEZEICHNUNG VARCHAR(100),
		STUCK INTEGER,
		ARTIKEL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ANTRIEB (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS ARTIKEL (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ABBILDUNG VARCHAR(255),
		ANMERKUNG VARCHAR(100),
		ARTIKEL_ID VARCHAR(6) NOT NULL,
		BELADUNG VARCHAR(100),
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		KAUFDATUM DATE,
		PREIS DECIMAL(6 , 2),
		STATUS VARCHAR(255) NOT NULL,
		STUCK INTEGER NOT NULL,
		VERBLEIBENDE INTEGER NOT NULL,
		WAHRUNG VARCHAR(3),
		DECODER_ID BIGINT,
		KUPPLUNG_ID BIGINT,
		LICHT_ID BIGINT,
		MOTOR_TYP_ID BIGINT,
		PRODUKT_ID BIGINT NOT NULL,
		STEUERUNG_ID BIGINT
	);

CREATE TABLE IF NOT EXISTS AUFBAU (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS BAHNVERWALTUNG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		LAND VARCHAR(2)
	);

CREATE TABLE IF NOT EXISTS DECODER_ADRESS (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ADRESS INTEGER NOT NULL,
		DECODER_ID BIGINT NOT NULL,
		ADRESS_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_CV (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		WERT INTEGER,
		CV_ID BIGINT NOT NULL,
		DECODER_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_FUNKTION (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		DECODER_ID BIGINT NOT NULL,
		FUNKTION_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		DECODER_ID VARCHAR(6) NOT NULL,
		FAHRSTUFE INTEGER,
		STATUS VARCHAR(255),
		DECODER_TYP_ID BIGINT NOT NULL,
		PROTOKOLL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP_ADRESS (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		WERKSEINSTELLUNG INTEGER,
		ADRESS_TYP INTEGER NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		POSITION INTEGER NOT NULL,
		SPAN INTEGER NOT NULL,
		DECODER_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP_CV (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100),
		CV INTEGER NOT NULL,
		MAXIMAL INTEGER,
		MINIMAL INTEGER,
		WERKSEINSTELLUNG INTEGER,
		DECODER_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP_FUNKTION (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100),
		FUNKTION VARCHAR(4),
		PROGRAMMABLE BOOLEAN NOT NULL,
		REIHE INTEGER NOT NULL,
		DECODER_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ANLEITUNGEN VARCHAR(512),
		BESTELL_NR VARCHAR(50),
		BEZEICHNUNG VARCHAR(100),
		FAHRSTUFE INTEGER,
		I_MAX DECIMAL(6 , 2),
		KONFIGURATION VARCHAR(15) NOT NULL,
		SOUND BOOLEAN NOT NULL,
		STECKER VARCHAR(10) NOT NULL,
		HERSTELLER_ID BIGINT NOT NULL,
		PROTOKOLL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS EPOCH (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS GATTUNG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS HERSTELLER (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		TELEFON VARCHAR(20),
		LAND VARCHAR(2),
		URL VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS KATEGORIE (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS KUPPLUNG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS LICHT (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS MASSSTAB (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS MOTOR_TYP (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS PRODUKT (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ABBILDUNG VARCHAR(255),
		ANLEITUNGEN VARCHAR(512),
		ANMERKUNG VARCHAR(100),
		BAUZEIT DATE,
		BESTELL_NR VARCHAR(50),
		BETREIBSNUMMER VARCHAR(100),
		BEZEICHNUNG VARCHAR(100),
		EXPLOSIONSZEICHNUNG VARCHAR(512),
		LANGE DECIMAL(6 , 2),
		ACHSFOLG_ID BIGINT,
		AUFBAU_ID BIGINT,
		BAHNVERWALTUNG_ID BIGINT,
		DECODER_TYP_ID BIGINT,
		EPOCH_ID BIGINT,
		GATTUNG_ID BIGINT,
		HERSTELLER_ID BIGINT NOT NULL,
		KUPPLUNG_ID BIGINT,
		LICHT_ID BIGINT,
		MASSSTAB_ID BIGINT NOT NULL,
		MOTOR_TYP_ID BIGINT,
		SONDERMODELL_ID BIGINT,
		SPURWEITE_ID BIGINT NOT NULL,
		STEUERUNG_ID BIGINT,
		UNTER_KATEGORIE_ID BIGINT NOT NULL,
		VORBILD_ID BIGINT
	);

CREATE TABLE IF NOT EXISTS PRODUKT_TEIL (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ANZAHL INTEGER NOT NULL,
		PRODUKT_ID BIGINT NOT NULL,
		TEIL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS PROTOKOLL (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS PERSON (
		ID INTEGER NOT NULL,
		CONFIRMATION_TOKEN VARCHAR(255),
		CREDENTIALS_EXPIRED BOOLEAN,
		EMAIL VARCHAR(255) NOT NULL,
		ENABLED BOOLEAN,
		EXPIRED BOOLEAN,
		FIRST_NAME VARCHAR(255) NOT NULL,
		LAST_NAME VARCHAR(255) NOT NULL,
		LOCKED BOOLEAN,
		RESET_TOKEN VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS PERSON_ROLES (
		PERSON_ID INTEGER NOT NULL,
		ROLES VARCHAR(255)
	);

CREATE TABLE IF NOT EXISTS SONDERMODELL (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS SPURWEITE (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS STEUERUNG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS UNTER_KATEGORIE (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50),
		KATEGORIE_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS VORBILD (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ABBILDUNG VARCHAR(255),
		ANFAHRZUGKRAFT DECIMAL(19 , 2),
		ANZAHL INTEGER,
		AUFBAU VARCHAR(100),
		AUSSERDIENST DATE,
		BAUZEIT DATE,
		BETREIBSNUMMER VARCHAR(100),
		BEZEICHNUNG VARCHAR(100),
		DIENSTGEWICHT DECIMAL(19 , 2),
		DM_LAUFRAD_HINTEN DECIMAL(19 , 2),
		DM_LAUFRAD_VORN DECIMAL(19 , 2),
		DM_TREIBRAD DECIMAL(19 , 2),
		DM_ZYLINDER DECIMAL(19 , 2),
		DREHGESTELL_BAUART VARCHAR(100),
		FAHRMOTOREN INTEGER,
		GESCHWINDIGKEIT INTEGER,
		HERSTELLER VARCHAR(100),
		KAPAZITAT DECIMAL(19 , 2),
		KESSELUBERDRUCK DECIMAL(19 , 2),
		KLASSE INTEGER,
		KOLBENHUB DECIMAL(19 , 2),
		LANGE DECIMAL(19 , 2),
		LEISTUNG DECIMAL(19 , 2),
		LEISTUNGSUBERTRAGUNG VARCHAR(255),
		MITTELWAGEN INTEGER,
		MOTORBAUART VARCHAR(100),
		REICHWEITE DECIMAL(19 , 2),
		ROSTFLACHE DECIMAL(19 , 2),
		SITZPLATZE_KL1 INTEGER,
		SITZPLATZE_KL2 INTEGER,
		SITZPLATZE_KL3 INTEGER,
		SITZPLATZE_KL4 INTEGER,
		TRIEBKOPF INTEGER,
		UBERHITZERFLACHE DECIMAL(19 , 2),
		VERDAMPFUNG DECIMAL(19 , 2),
		WASSERVORRAT DECIMAL(19 , 2),
		ZYLINDER INTEGER,
		ACHSFOLG_ID BIGINT NOT NULL,
		ANTRIEB_ID BIGINT NOT NULL,
		BAHNVERWALTUNG_ID BIGINT NOT NULL,
		GATTUNG_ID BIGINT NOT NULL,
		UNTER_KATEGORIE_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ZUG_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG_CONSIST (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		POSITION INTEGER NOT NULL,
		ARTIKEL_ID BIGINT NOT NULL,
		ZUG_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG_TYP (
		ID BIGINT NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE UNIQUE INDEX IF NOT EXISTS ACHSFOLG_PK_UC ON ACHSFOLG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ANDERUNG_PK_UC ON ANDERUNG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ANTRIEB_PK_UC ON ANTRIEB(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ARTIKEL_PK_UC ON ARTIKEL(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS AUFBAU_PK_UC ON AUFBAU(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS BAHNVERWALTUNG_PK_UC ON BAHNVERWALTUNG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_ADRESS_PK_UC ON DECODER_ADRESS(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_CV_PK_UC ON DECODER_CV(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_FUNKTION_PK_UC ON DECODER_FUNKTION(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_PK_UC ON DECODER(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_TYP_FUNKTION_PK_UC ON DECODER_TYP_FUNKTION(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_TYP_CV_PK_UC ON DECODER_TYP_CV(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_TYP_ADRESS_PK_UC ON DECODER_TYP_ADRESS(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS DECODER_TYP_PK_UC ON DECODER_TYP(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS EPOCH_PK_UC ON EPOCH(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS GATTUNG_PK_UC ON GATTUNG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS HERSTELLER_PK_UC ON HERSTELLER(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS KATEGORIE_PK_UC ON KATEGORIE(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS KUPPLUNG_PK_UC ON KUPPLUNG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS LICHT_PK_UC ON LICHT(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS MASSSTAB_PK_UC ON MASSSTAB(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS MOTOR_TYP_PK_UC ON MOTOR_TYP(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS PERSON_PK_UC ON PERSON(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS PRODUKT_PK_UC ON PRODUKT(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS PRODUKT_TEIL_PK_UC ON PRODUKT_TEIL(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS SONDERMODELL_PK_UC ON SONDERMODELL(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS SPURWEITE_PK_UC ON SPURWEITE(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS STEUERUNG_PK_UC ON STEUERUNG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS UNTER_KATEGORIE_PK_UC ON UNTER_KATEGORIE(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS VORBILD_PK_UC ON VORBILD(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ZUG_PK_UC ON ZUG(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ZUG_TYP_PK_UC ON ZUG_TYP(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS PROTOKOLL_PK_UC ON PROTOKOLL(ID ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ZUG_CONSIST_PK_UC ON ZUG_CONSIST(ID ASC);

CREATE INDEX IF NOT EXISTS ANDERUNG_IX1 ON ANDERUNG (ARTIKEL_ID ASC);
CREATE INDEX IF NOT EXISTS ANDERUNG_IX2 ON ANDERUNG (ANDERUNG_ID ASC);

CREATE INDEX IF NOT EXISTS ARTIKEL_IX1 ON ARTIKEL (PRODUKT_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX3 ON ARTIKEL (STEUERUNG_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX4 ON ARTIKEL (MOTOR_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX5 ON ARTIKEL (LICHT_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX6 ON ARTIKEL (KUPPLUNG_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX7 ON ARTIKEL (DECODER_ID ASC);

CREATE INDEX IF NOT EXISTS DECODERADRESS_FK1_INDEX_7 ON DECODER_ADRESS (DECODER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERADRESS_FK2_INDEX_7 ON DECODER_ADRESS (ADRESS_ID ASC);

CREATE INDEX IF NOT EXISTS DECODERCV_FK1_INDEX_D ON DECODER_CV (DECODER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERCV_FK2_INDEX_D ON DECODER_CV (CV_ID ASC);

CREATE INDEX IF NOT EXISTS DECODERFUNKTION_FK1_INDEX_C ON DECODER_FUNKTION (DECODER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERFUNKTION_FK2_INDEX_C ON DECODER_FUNKTION (FUNKTION_ID ASC);

CREATE INDEX IF NOT EXISTS DECODERTYPADRESS_IX1 ON DECODER_TYP_ADRESS (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERTYPADRESS_IX2 ON DECODER_TYP_ADRESS (POSITION ASC);

CREATE INDEX IF NOT EXISTS DECODERTYPCV_IX1 ON DECODER_TYP_CV (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERTYPCV_IX2 ON DECODER_TYP_CV (CV ASC);

CREATE INDEX IF NOT EXISTS DECODERTYPFUNKTION_IX1 ON DECODER_TYP_FUNKTION (DECODER_TYP_ID ASC);

CREATE INDEX IF NOT EXISTS DECODERTYP_IX1 ON DECODER_TYP (HERSTELLER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODERTYP_IX2 ON DECODER_TYP (PROTOKOLL_ID ASC);

CREATE INDEX IF NOT EXISTS DECODER_IX1 ON DECODER (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_IX2 ON DECODER (PROTOKOLL_ID ASC);

CREATE INDEX IF NOT EXISTS FKS955LUJ19XYJWI3S1OMO1PGH4_INDEX_A ON PERSON_ROLES (PERSON_ID ASC);

CREATE INDEX IF NOT EXISTS PRODUKTTEIL_IX1 ON PRODUKT_TEIL (PRODUKT_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKTTEIL_IX2 ON PRODUKT_TEIL (TEIL_ID ASC);

CREATE INDEX IF NOT EXISTS PRODUKT_IX1 ON PRODUKT (HERSTELLER_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX2 ON PRODUKT (EPOCH_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX3 ON PRODUKT (GATTUNG_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX4 ON PRODUKT (BAHNVERWALTUNG_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX5 ON PRODUKT (ACHSFOLG_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX6 ON PRODUKT (MASSSTAB_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX7 ON PRODUKT (SPURWEITE_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX8 ON PRODUKT (UNTER_KATEGORIE_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX9 ON PRODUKT (SONDERMODELL_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX10 ON PRODUKT (AUFBAU_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX11 ON PRODUKT (LICHT_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX12 ON PRODUKT (KUPPLUNG_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX13 ON PRODUKT (VORBILD_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX14 ON PRODUKT (STEUERUNG_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX15 ON PRODUKT (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_IX16 ON PRODUKT (MOTOR_TYP_ID ASC);

CREATE INDEX IF NOT EXISTS UNTERKATEGORIE_IX1 ON UNTER_KATEGORIE (KATEGORIE_ID ASC);

CREATE INDEX IF NOT EXISTS VORBILD_FK3_INDEX_5 ON VORBILD (BAHNVERWALTUNG_ID ASC);

CREATE INDEX IF NOT EXISTS VORBILD_IX1 ON VORBILD (UNTER_KATEGORIE_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX2 ON VORBILD (ANTRIEB_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX3 ON VORBILD (ACHSFOLG_ID ASC);

CREATE INDEX IF NOT EXISTS ZUGCONSIST_IX1 ON ZUG_CONSIST (ZUG_ID ASC);
CREATE INDEX IF NOT EXISTS ZUGCONSIST_IX2 ON ZUG_CONSIST (ARTIKEL_ID ASC);

CREATE INDEX IF NOT EXISTS ZUG_IX1 ON ZUG (ZUG_TYP_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS ACHSFOLG_UC1_INDEX_B ON ACHSFOLG (NAME ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ANDERUNG_UC1_INDEX_F ON ANDERUNG (ARTIKEL_ID ASC, ANDERUNG_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS ANTRIEB_UC1_INDEX_F ON ANTRIEB (NAME ASC);
CREATE UNIQUE INDEX IF NOT EXISTS ARTIKEL_UC1_INDEX_F ON ARTIKEL (ARTIKEL_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS AUFBAU_UC1_INDEX_7 ON AUFBAU (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS BAHNVERWALTUNG_UC1_INDEX_C ON BAHNVERWALTUNG (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERADRESS_UC1_INDEX_7 ON DECODER_ADRESS (DECODER_ID ASC, ADRESS_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERCV_UC1_INDEX_D ON DECODER_CV (DECODER_ID ASC, CV_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERFUNKTION_UC1_INDEX_C ON DECODER_FUNKTION (DECODER_ID ASC, FUNKTION_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERTYPADRESS_UC1_INDEX_F ON DECODER_TYP_ADRESS (DECODER_TYP_ID ASC, POSITION ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERTYPCV_UC1_INDEX_9 ON DECODER_TYP_CV (DECODER_TYP_ID ASC, CV ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERTYPFUNKTION_UC1_INDEX_7 ON DECODER_TYP_FUNKTION (DECODER_TYP_ID ASC, REIHE ASC, FUNKTION ASC);

CREATE UNIQUE INDEX IF NOT EXISTS DECODERTYP_UC1_INDEX_C ON DECODER_TYP (HERSTELLER_ID ASC, BESTELL_NR ASC);

CREATE UNIQUE INDEX IF NOT EXISTS EPOCH_UC1_INDEX_3 ON EPOCH (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS GATTUNG_UC1_INDEX_1 ON GATTUNG (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS HERSTELLER_UC1_INDEX_A ON HERSTELLER (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS KATEGORIE_UC1_INDEX_A ON KATEGORIE (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS KUPPLUNG_UC1_INDEX_8 ON KUPPLUNG (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS LICHT_UC1_INDEX_4 ON LICHT (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS MASSSTAB_UC1_INDEX_3 ON MASSSTAB (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS MOTORTYP_UC1_INDEX_8 ON MOTOR_TYP (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS PERSON_UC1_INDEX_8 ON PERSON (EMAIL ASC);

CREATE UNIQUE INDEX IF NOT EXISTS PRODUKTTEIL_UC1_INDEX_6 ON PRODUKT_TEIL (PRODUKT_ID ASC, TEIL_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS PRODUKT_UC1_INDEX_1 ON PRODUKT (HERSTELLER_ID ASC, BESTELL_NR ASC);

CREATE UNIQUE INDEX IF NOT EXISTS PROTOKOLL_UC1_INDEX_8 ON PROTOKOLL (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS SONDERMODELL_UC1_INDEX_B ON SONDERMODELL (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS SPURWEITE_UC1_INDEX_A ON SPURWEITE (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS STEUERUNG_UC1_INDEX_F ON STEUERUNG (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS UK_ORDEVYWLK5437FT6BXP996JPE_INDEX_8 ON DECODER (DECODER_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS UNTERKATEGORIE_UC1_INDEX_F ON UNTER_KATEGORIE (KATEGORIE_ID ASC, NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS VORBILD_UC1_INDEX_5 ON VORBILD (GATTUNG_ID ASC);

CREATE UNIQUE INDEX IF NOT EXISTS ZUGCONSIST_UC1_INDEX_8 ON ZUG_CONSIST (ZUG_ID ASC, POSITION ASC);

CREATE UNIQUE INDEX IF NOT EXISTS ZUGTYP_UC1_INDEX_2 ON ZUG_TYP (NAME ASC);

CREATE UNIQUE INDEX IF NOT EXISTS ZUG_UC1_INDEX_1 ON ZUG (NAME ASC);

ALTER TABLE IF EXISTS ACHSFOLG ADD CONSTRAINT IF NOT EXISTS ACHSFOLG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS ACHSFOLG ADD CONSTRAINT IF NOT EXISTS ACHSFOLG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS ANDERUNG ADD CONSTRAINT IF NOT EXISTS ANDERUNG_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS ANTRIEB ADD CONSTRAINT IF NOT EXISTS ANTRIEB_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS ANTRIEB ADD CONSTRAINT IF NOT EXISTS ANTRIEB_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS AUFBAU ADD CONSTRAINT IF NOT EXISTS AUFBAU_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS AUFBAU ADD CONSTRAINT IF NOT EXISTS AUFBAU_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS BAHNVERWALTUNG ADD CONSTRAINT IF NOT EXISTS BAHNVERWALTUNG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS BAHNVERWALTUNG ADD CONSTRAINT IF NOT EXISTS BAHNVERWALTUNG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS DECODER_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODER_ADRESS_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODER_CV_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_FUNKTION_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS DECODER_TYP_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_ADRESS_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS DECODER_TYP_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_ADRESS_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS DECODER_TYP_CV ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_CV_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS DECODER_TYP_CV ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_CV_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS DECODER_TYP_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FUNKTION_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS DECODER_TYP_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FUNKTION_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS EPOCH ADD CONSTRAINT IF NOT EXISTS EPOCH_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS EPOCH ADD CONSTRAINT IF NOT EXISTS EPOCH_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS GATTUNG ADD CONSTRAINT IF NOT EXISTS GATTUNG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS GATTUNG ADD CONSTRAINT IF NOT EXISTS GATTUNG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS HERSTELLER ADD CONSTRAINT IF NOT EXISTS HERSTELLER_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS HERSTELLER ADD CONSTRAINT IF NOT EXISTS HERSTELLER_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS KATEGORIE ADD CONSTRAINT IF NOT EXISTS KATEGORIE_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS KATEGORIE ADD CONSTRAINT IF NOT EXISTS KATEGORIE_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS KUPPLUNG ADD CONSTRAINT IF NOT EXISTS KUPPLUNG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS KUPPLUNG ADD CONSTRAINT IF NOT EXISTS KUPPLUNG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS LICHT ADD CONSTRAINT IF NOT EXISTS LICHT_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS LICHT ADD CONSTRAINT IF NOT EXISTS LICHT_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS MASSSTAB ADD CONSTRAINT IF NOT EXISTS MASSSTAB_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS MASSSTAB ADD CONSTRAINT IF NOT EXISTS MASSSTAB_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS MOTOR_TYP ADD CONSTRAINT IF NOT EXISTS MOTOR_TYP_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS MOTOR_TYP ADD CONSTRAINT IF NOT EXISTS MOTOR_TYP_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS PERSON ADD CONSTRAINT IF NOT EXISTS PERSON_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS PERSON ADD CONSTRAINT IF NOT EXISTS PERSON_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKT_TEIL_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS PROTOKOLL ADD CONSTRAINT IF NOT EXISTS PROTOKOLL_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS PROTOKOLL ADD CONSTRAINT IF NOT EXISTS PROTOKOLL_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS SONDERMODELL ADD CONSTRAINT IF NOT EXISTS SONDERMODELL_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS SONDERMODELL ADD CONSTRAINT IF NOT EXISTS SONDERMODELL_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS SPURWEITE ADD CONSTRAINT IF NOT EXISTS SPURWEITE_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS SPURWEITE ADD CONSTRAINT IF NOT EXISTS SPURWEITE_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS STEUERUNG ADD CONSTRAINT IF NOT EXISTS STEUERUNG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS STEUERUNG ADD CONSTRAINT IF NOT EXISTS STEUERUNG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS UNTER_KATEGORIE ADD CONSTRAINT IF NOT EXISTS UNTER_KATEGORIE_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS UNTER_KATEGORIE ADD CONSTRAINT IF NOT EXISTS UNTER_KATEGORIE_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS ZUG ADD CONSTRAINT IF NOT EXISTS ZUG_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS ZUG ADD CONSTRAINT IF NOT EXISTS ZUG_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUG_CONSIST_PK PRIMARY KEY (ID);

ALTER TABLE IF EXISTS ZUG_TYP ADD CONSTRAINT IF NOT EXISTS ZUG_TYP_PK PRIMARY KEY (ID);
ALTER TABLE IF EXISTS ZUG_TYP ADD CONSTRAINT IF NOT EXISTS ZUG_TYP_PK_IX UNIQUE (ID);

ALTER TABLE IF EXISTS ANDERUNG ADD CONSTRAINT IF NOT EXISTS ANDERUNG_FK1 FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK1 FOREIGN KEY (PRODUKT_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK3 FOREIGN KEY (STEUERUNG_ID) REFERENCES STEUERUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK4 FOREIGN KEY (MOTOR_TYP_ID) REFERENCES MOTOR_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK5 FOREIGN KEY (LICHT_ID) REFERENCES LICHT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK6 FOREIGN KEY (KUPPLUNG_ID) REFERENCES KUPPLUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK7 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_FK2 FOREIGN KEY (PROTOKOLL_ID) REFERENCES PROTOKOLL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODERADRESS_FK1 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODERADRESS_FK2 FOREIGN KEY (ADRESS_ID) REFERENCES DECODER_TYP_ADRESS (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODERCV_FK1 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODERCV_FK2 FOREIGN KEY (CV_ID) REFERENCES DECODER_TYP_CV (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODERFUNKTION_FK1 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODERFUNKTION_FK2 FOREIGN KEY (FUNKTION_ID) REFERENCES DECODER_TYP_FUNKTION (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODERTYP_FK2 FOREIGN KEY (HERSTELLER_ID) REFERENCES HERSTELLER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODERTYP_FK3 FOREIGN KEY (PROTOKOLL_ID) REFERENCES PROTOKOLL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP_ADRESS ADD CONSTRAINT IF NOT EXISTS DECODERTYPADRESS_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP_CV ADD CONSTRAINT IF NOT EXISTS DECODERTYPCV_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODERTYPFUNKTION_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS PERSON_ROLES ADD CONSTRAINT IF NOT EXISTS FKS955LUJ19XYJWI3S1OMO1PGH4 FOREIGN KEY (PERSON_ID) REFERENCES PERSON (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK1 FOREIGN KEY (EPOCH_ID) REFERENCES EPOCH (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK2 FOREIGN KEY (GATTUNG_ID) REFERENCES GATTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK3 FOREIGN KEY (BAHNVERWALTUNG_ID) REFERENCES BAHNVERWALTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK4 FOREIGN KEY (ACHSFOLG_ID) REFERENCES ACHSFOLG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK5 FOREIGN KEY (MASSSTAB_ID) REFERENCES MASSSTAB (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK6 FOREIGN KEY (SPURWEITE_ID) REFERENCES SPURWEITE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK7 FOREIGN KEY (UNTER_KATEGORIE_ID) REFERENCES UNTER_KATEGORIE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK8 FOREIGN KEY (SONDERMODELL_ID) REFERENCES SONDERMODELL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK9 FOREIGN KEY (AUFBAU_ID) REFERENCES AUFBAU (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK10 FOREIGN KEY (LICHT_ID) REFERENCES LICHT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK11 FOREIGN KEY (KUPPLUNG_ID) REFERENCES KUPPLUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK12 FOREIGN KEY (VORBILD_ID) REFERENCES VORBILD (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK13 FOREIGN KEY (STEUERUNG_ID) REFERENCES STEUERUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK14 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK15 FOREIGN KEY (MOTOR_TYP_ID) REFERENCES MOTOR_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_FK16 FOREIGN KEY (HERSTELLER_ID) REFERENCES HERSTELLER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKTTEIL_FK1 FOREIGN KEY (PRODUKT_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKTTEIL_FK2 FOREIGN KEY (TEIL_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS UNTER_KATEGORIE ADD CONSTRAINT IF NOT EXISTS UNTERKATEGORIE_FK1 FOREIGN KEY (KATEGORIE_ID) REFERENCES KATEGORIE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK1 FOREIGN KEY (GATTUNG_ID) REFERENCES GATTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK2 FOREIGN KEY (UNTER_KATEGORIE_ID) REFERENCES UNTER_KATEGORIE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK3 FOREIGN KEY (BAHNVERWALTUNG_ID) REFERENCES BAHNVERWALTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK4 FOREIGN KEY (ANTRIEB_ID) REFERENCES ANTRIEB (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK5 FOREIGN KEY (ACHSFOLG_ID) REFERENCES ACHSFOLG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ZUG ADD CONSTRAINT IF NOT EXISTS ZUG_FK1 FOREIGN KEY (ZUG_TYP_ID) REFERENCES ZUG_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUGCONSIST_FK1 FOREIGN KEY (ZUG_ID) REFERENCES ZUG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUGCONSIST_FK2 FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
