--<ScriptOptions statementTerminator=";"/>

CREATE TABLE IF NOT EXISTS ACHSFOLG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS ANDERUNG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ANDERUNG_ID INTEGER NOT NULL,
		ANDERUNGSDATUM DATE,
		ANDERUNGS_TYP VARCHAR(14) NOT NULL,
		ANMERKUNG VARCHAR(255),
		BEZEICHNUNG VARCHAR(100),
		MENGE INTEGER,
		ARTIKEL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ANTRIEB (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS ARTIKEL (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ABBILDUNG VARCHAR(512),
		GROSSANSICHT VARCHAR(512),
		ANMERKUNG VARCHAR(100),
		ARTIKEL_ID VARCHAR(6) NOT NULL,
		BELADUNG VARCHAR(100),
		BEZEICHNUNG VARCHAR(100),
		KAUFDATUM DATE,
		PREIS DECIMAL(6 , 2),
		STATUS VARCHAR(12) NOT NULL,
		MENGE INTEGER NOT NULL,
		VERBLEIBENDE INTEGER NOT NULL,
		WAHRUNG VARCHAR(3),
		KUPPLUNG_ID BIGINT,
		LICHT_ID BIGINT,
		MOTOR_TYP_ID BIGINT,
		PRODUKT_ID BIGINT NOT NULL,
		STEUERUNG_ID BIGINT
	);

CREATE TABLE IF NOT EXISTS AUFBAU (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS BAHNVERWALTUNG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		LAND VARCHAR(2),
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS DECODER (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		DECODER_ID VARCHAR(6) NOT NULL,
		ARTIKEL_ID BIGINT,
		ADRESS INTEGER,
		FAHRSTUFE INTEGER,
		KAUFDATUM DATE,
		WAHRUNG VARCHAR(3),
		PREIS DECIMAL(6 , 2),
		ANMERKUNG VARCHAR(100),
		STATUS VARCHAR(11),
		DECODER_TYP_ID BIGINT NOT NULL,
		PROTOKOLL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_CV (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		WERT INTEGER,
		CV_ID BIGINT NOT NULL,
		DECODER_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_FUNKTION (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		DECODER_ID BIGINT NOT NULL,
		FUNKTION_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ANLEITUNGEN VARCHAR(512),
		BESTELL_NR VARCHAR(50),
		BEZEICHNUNG VARCHAR(100),
		ADRESS_TYP VARCHAR(14) NOT NULL,
		ADRESS INTEGER,
		SPAN INTEGER NOT NULL,
		FAHRSTUFE INTEGER,
		I_MAX DECIMAL(6 , 2),
		KONFIGURATION VARCHAR(15) NOT NULL,
		SOUND BOOLEAN NOT NULL,
		MOTOR BOOLEAN DEFAULT TRUE NOT NULL,
		OUTPUTS INTEGER DEFAULT 1 NOT NULL,
		SERVOS INTEGER DEFAULT 0 NOT NULL,
		STECKER VARCHAR(10) NOT NULL,
		HERSTELLER_ID BIGINT NOT NULL,
		PROTOKOLL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP_CV (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100),
		CV INTEGER NOT NULL,
		MAXIMAL INTEGER,
		MINIMAL INTEGER,
		WERKSEINSTELLUNG INTEGER,
		DECODER_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS DECODER_TYP_FUNKTION (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100),
		FUNKTION VARCHAR(4),
		PROGRAMMABLE BOOLEAN NOT NULL,
		DECODER_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS EPOCH (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		START_YEAR INTEGER,
		END_YEAR INTEGER,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS GATTUNG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS HERSTELLER (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		TELEFON VARCHAR(20),
		LAND VARCHAR(2),
		URL VARCHAR(255),
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS KATEGORIE (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS KUPPLUNG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS LICHT (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS MASSSTAB (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS MOTOR_TYP (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS PERSON (
		ID IDENTITY NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		PASSWORD VARCHAR(100) NOT NULL,
		EMAIL VARCHAR(100) NOT NULL,
		FIRST_NAME VARCHAR(50),
		LAST_NAME VARCHAR(50),
		ENABLED BOOLEAN DEFAULT FALSE NOT NULL,
		LOGIN_ATTEMPTS INTEGER,
		LOGIN_FAILURES INTEGER,
		PASSWORD_AGING INTEGER,
		PASSWORD_CHANGED TIMESTAMP,
		LAST_LOGIN TIMESTAMP,
		LOCALE VARCHAR(50),
		CONFIRMATION_TOKEN VARCHAR(36),
		CONFIRMATION_EXPIRES TIMESTAMP
	);

CREATE TABLE IF NOT EXISTS PERSON_ROLES (
		PERSON_ID INTEGER NOT NULL,
		ROLES VARCHAR(50)
	);

CREATE TABLE IF NOT EXISTS PRODUKT (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		ABBILDUNG VARCHAR(512),
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
		GROSSANSICHT VARCHAR(512),
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
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		MENGE INTEGER NOT NULL,
		PRODUKT_ID BIGINT NOT NULL,
		TEIL_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS PROTOKOLL (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS SONDERMODELL (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS SPURWEITE (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS STEUERUNG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ABBILDUNG VARCHAR(512)
	);

CREATE TABLE IF NOT EXISTS UNTER_KATEGORIE (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		KATEGORIE_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS VORBILD (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		NAME VARCHAR(50),
		ABBILDUNG VARCHAR(512),
		ANFAHRZUGKRAFT DECIMAL(19 , 2),
		MENGE INTEGER,
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
		LEISTUNGSUBERTRAGUNG VARCHAR(14),
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
		ACHSFOLG_ID BIGINT,
		ANTRIEB_ID BIGINT,
		BAHNVERWALTUNG_ID BIGINT,
		GATTUNG_ID BIGINT NOT NULL,
		UNTER_KATEGORIE_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL,
		ZUG_TYP_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG_CONSIST (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		POSITION INTEGER NOT NULL,
		ARTIKEL_ID BIGINT NOT NULL,
		ZUG_ID BIGINT NOT NULL
	);

CREATE TABLE IF NOT EXISTS ZUG_TYP (
		ID IDENTITY NOT NULL,
		DELETED BOOLEAN NOT NULL,
		BEZEICHNUNG VARCHAR(100) NOT NULL,
		NAME VARCHAR(50) NOT NULL
	);

CREATE INDEX IF NOT EXISTS ANDERUNG_IX1 ON ANDERUNG (ARTIKEL_ID ASC);
CREATE INDEX IF NOT EXISTS ANDERUNG_IX2 ON ANDERUNG (ANDERUNG_ID ASC);

CREATE INDEX IF NOT EXISTS ARTIKEL_IX1 ON ARTIKEL (PRODUKT_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX3 ON ARTIKEL (STEUERUNG_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX4 ON ARTIKEL (MOTOR_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX5 ON ARTIKEL (LICHT_ID ASC);
CREATE INDEX IF NOT EXISTS ARTIKEL_IX6 ON ARTIKEL (KUPPLUNG_ID ASC);

CREATE INDEX IF NOT EXISTS DECODER_CV_IX1 ON DECODER_CV (DECODER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_CV_IX2 ON DECODER_CV (CV_ID ASC);

CREATE INDEX IF NOT EXISTS DECODER_FUNKTION_IX1 ON DECODER_FUNKTION (DECODER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_FUNKTION_IX2 ON DECODER_FUNKTION (FUNKTION_ID ASC);

CREATE INDEX IF NOT EXISTS DECODER_TYP_CV_IX1 ON DECODER_TYP_CV (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_TYP_CV_IX2 ON DECODER_TYP_CV (CV ASC);

CREATE INDEX IF NOT EXISTS DECODER_TYP_FUNKTION_IX1 ON DECODER_TYP_FUNKTION (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_TYP_FUNKTION_IX2 ON DECODER_TYP_FUNKTION (FUNKTION ASC);

CREATE INDEX IF NOT EXISTS DECODER_TYP_IX1 ON DECODER_TYP (HERSTELLER_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_TYP_IX2 ON DECODER_TYP (PROTOKOLL_ID ASC);

CREATE INDEX IF NOT EXISTS DECODER_IX1 ON DECODER (DECODER_TYP_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_IX2 ON DECODER (PROTOKOLL_ID ASC);
CREATE INDEX IF NOT EXISTS DECODER_IX3 ON ARTIKEL (ARTIKEL_ID ASC);

CREATE INDEX IF NOT EXISTS PERSON_IX1 ON PERSON (EMAIL ASC);

CREATE INDEX IF NOT EXISTS PERSON_ROLES_IX1 ON PERSON_ROLES (PERSON_ID ASC);

CREATE INDEX IF NOT EXISTS PRODUKT_TEIL_IX1 ON PRODUKT_TEIL (PRODUKT_ID ASC);
CREATE INDEX IF NOT EXISTS PRODUKT_TEIL_IX2 ON PRODUKT_TEIL (TEIL_ID ASC);

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

CREATE INDEX IF NOT EXISTS UNTER_KATEGORIE_IX1 ON UNTER_KATEGORIE (KATEGORIE_ID ASC);

CREATE INDEX IF NOT EXISTS VORBILD_IX1 ON VORBILD (ANTRIEB_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX2 ON VORBILD (ACHSFOLG_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX3 ON VORBILD (BAHNVERWALTUNG_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX4 ON VORBILD (GATTUNG_ID ASC);
CREATE INDEX IF NOT EXISTS VORBILD_IX5 ON VORBILD (UNTER_KATEGORIE_ID ASC);

CREATE INDEX IF NOT EXISTS ZUG_CONSIST_IX1 ON ZUG_CONSIST (ZUG_ID ASC);
CREATE INDEX IF NOT EXISTS ZUG_CONSIST_IX2 ON ZUG_CONSIST (ARTIKEL_ID ASC);

CREATE INDEX IF NOT EXISTS ZUG_IX1 ON ZUG (ZUG_TYP_ID ASC);

ALTER TABLE IF EXISTS ACHSFOLG ADD CONSTRAINT IF NOT EXISTS ACHSFOLG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS ANDERUNG ADD CONSTRAINT IF NOT EXISTS ANDERUNG_UC1 UNIQUE (ARTIKEL_ID ASC, ANDERUNG_ID ASC);

ALTER TABLE IF EXISTS ANTRIEB ADD CONSTRAINT IF NOT EXISTS ANTRIEB_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_UC1 UNIQUE (ARTIKEL_ID ASC);

ALTER TABLE IF EXISTS AUFBAU ADD CONSTRAINT IF NOT EXISTS AUFBAU_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS BAHNVERWALTUNG ADD CONSTRAINT IF NOT EXISTS BAHNVERWALTUNG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_UC1 UNIQUE (DECODER_ID ASC);

ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODER_CV_UC1 UNIQUE (DECODER_ID ASC, CV_ID ASC);

ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_FUNKTION_UC1 UNIQUE (DECODER_ID ASC, FUNKTION_ID ASC);

ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_UC1 UNIQUE (HERSTELLER_ID ASC, BESTELL_NR ASC);

ALTER TABLE IF EXISTS DECODER_TYP_CV ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_CV_UC1 UNIQUE (DECODER_TYP_ID ASC, CV ASC);

ALTER TABLE IF EXISTS DECODER_TYP_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FUNKTION_UC1 UNIQUE (DECODER_TYP_ID ASC, FUNKTION ASC);

ALTER TABLE IF EXISTS EPOCH ADD CONSTRAINT IF NOT EXISTS EPOCH_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS GATTUNG ADD CONSTRAINT IF NOT EXISTS GATTUNG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS HERSTELLER ADD CONSTRAINT IF NOT EXISTS HERSTELLER_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS KATEGORIE ADD CONSTRAINT IF NOT EXISTS KATEGORIE_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS KUPPLUNG ADD CONSTRAINT IF NOT EXISTS KUPPLUNG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS LICHT ADD CONSTRAINT IF NOT EXISTS LICHT_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS MASSSTAB ADD CONSTRAINT IF NOT EXISTS MASSSTAB_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS MOTOR_TYP ADD CONSTRAINT IF NOT EXISTS MOTORTYP_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS PERSON ADD CONSTRAINT IF NOT EXISTS PERSON_UC1 UNIQUE (EMAIL ASC);

ALTER TABLE IF EXISTS PERSON_ROLES ADD CONSTRAINT IF NOT EXISTS PERSON_ROLES_UC1 UNIQUE (PERSON_ID ASC, ROLES ASC);

ALTER TABLE IF EXISTS PRODUKT ADD CONSTRAINT IF NOT EXISTS PRODUKT_UC1 UNIQUE (HERSTELLER_ID ASC, BESTELL_NR ASC);

ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKT_TEIL_UC1 UNIQUE (PRODUKT_ID ASC, TEIL_ID ASC);

ALTER TABLE IF EXISTS PROTOKOLL ADD CONSTRAINT IF NOT EXISTS PROTOKOLL_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS SONDERMODELL ADD CONSTRAINT IF NOT EXISTS SONDERMODELL_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS SPURWEITE ADD CONSTRAINT IF NOT EXISTS SPURWEITE_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS STEUERUNG ADD CONSTRAINT IF NOT EXISTS STEUERUNG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS UNTER_KATEGORIE ADD CONSTRAINT IF NOT EXISTS UNTER_KATEGORIE_UC1 UNIQUE (KATEGORIE_ID ASC, NAME ASC);

ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS ZUG ADD CONSTRAINT IF NOT EXISTS ZUG_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUG_CONSIST_UC1 UNIQUE (ZUG_ID ASC, POSITION ASC);

ALTER TABLE IF EXISTS ZUG_TYP ADD CONSTRAINT IF NOT EXISTS ZUG_TYP_UC1 UNIQUE (NAME ASC);

ALTER TABLE IF EXISTS ANDERUNG ADD CONSTRAINT IF NOT EXISTS ANDERUNG_FK1 FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK1 FOREIGN KEY (PRODUKT_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK3 FOREIGN KEY (STEUERUNG_ID) REFERENCES STEUERUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK4 FOREIGN KEY (MOTOR_TYP_ID) REFERENCES MOTOR_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK5 FOREIGN KEY (LICHT_ID) REFERENCES LICHT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ARTIKEL ADD CONSTRAINT IF NOT EXISTS ARTIKEL_FK6 FOREIGN KEY (KUPPLUNG_ID) REFERENCES KUPPLUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_FK2 FOREIGN KEY (PROTOKOLL_ID) REFERENCES PROTOKOLL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER ADD CONSTRAINT IF NOT EXISTS DECODER_FK3 FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ID) ON DELETE SET NULL ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODERCV_FK1 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_CV ADD CONSTRAINT IF NOT EXISTS DECODERCV_FK2 FOREIGN KEY (CV_ID) REFERENCES DECODER_TYP_CV (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODERFUNKTION_FK1 FOREIGN KEY (DECODER_ID) REFERENCES DECODER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODERFUNKTION_FK2 FOREIGN KEY (FUNKTION_ID) REFERENCES DECODER_TYP_FUNKTION (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FK2 FOREIGN KEY (HERSTELLER_ID) REFERENCES HERSTELLER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS DECODER_TYP ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FK3 FOREIGN KEY (PROTOKOLL_ID) REFERENCES PROTOKOLL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP_CV ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_CV_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS DECODER_TYP_FUNKTION ADD CONSTRAINT IF NOT EXISTS DECODER_TYP_FUNKTION_FK1 FOREIGN KEY (DECODER_TYP_ID) REFERENCES DECODER_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS PERSON_ROLES ADD CONSTRAINT IF NOT EXISTS PERSON_ROLES_FK1 FOREIGN KEY (PERSON_ID) REFERENCES PERSON (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

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

ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKT_TEIL_FK1 FOREIGN KEY (PRODUKT_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS PRODUKT_TEIL ADD CONSTRAINT IF NOT EXISTS PRODUKT_TEIL_FK2 FOREIGN KEY (TEIL_ID) REFERENCES PRODUKT (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS UNTER_KATEGORIE ADD CONSTRAINT IF NOT EXISTS UNTER_KATEGORIE_FK1 FOREIGN KEY (KATEGORIE_ID) REFERENCES KATEGORIE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK1 FOREIGN KEY (GATTUNG_ID) REFERENCES GATTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK2 FOREIGN KEY (UNTER_KATEGORIE_ID) REFERENCES UNTER_KATEGORIE (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK3 FOREIGN KEY (BAHNVERWALTUNG_ID) REFERENCES BAHNVERWALTUNG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK4 FOREIGN KEY (ANTRIEB_ID) REFERENCES ANTRIEB (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS VORBILD ADD CONSTRAINT IF NOT EXISTS VORBILD_FK5 FOREIGN KEY (ACHSFOLG_ID) REFERENCES ACHSFOLG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ZUG ADD CONSTRAINT IF NOT EXISTS ZUG_FK1 FOREIGN KEY (ZUG_TYP_ID) REFERENCES ZUG_TYP (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUG_CONSIST_FK1 FOREIGN KEY (ZUG_ID) REFERENCES ZUG (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE IF EXISTS ZUG_CONSIST ADD CONSTRAINT IF NOT EXISTS ZUG_CONSIST_FK2 FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;