package com.linepro.modellbahn.persistence;

public interface DBNames {

    /** Entities */
    String ACHSFOLG = "achsfolg";
    String ANDERUNG = "anderung";
    String ANDERUNGEN = "anderungen";
    String ANTRIEB = "antrieb";
    String ARTIKEL = "artikel";
    String AUFBAU = "aufbau";
    String BAHNVERWALTUNG = "bahnverwaltung";
    String STECKER = "stecker";
    String DECODER = "decoder";
    String DECODER_ADRESS = "decoderAdress";
    String DECODER_CV = "decoderCv";
    String DECODER_FUNKTION = "decoderFunktion";
    String DECODER_TYP = "decoderTyp";
    String DECODER_TYP_ADRESS = "decoderTypAdress";
    String DECODER_TYP_CV = "decoderTypCv";
    String DECODER_TYP_FUNKTION = "decoderTypFunktion";
    String EPOCH = "epoch";
    String GATTUNG = "gattung";
    String HERSTELLER = "hersteller";
    String KATEGORIE = "kategorie";
    String KUPPLUNG = "kupplung";
    String LAND = "land";
    String LICHT = "licht";
    String MASSSTAB = "massstab";
    String MOTOR_TYP = "motorTyp";
    String PRODUKT = "produkt";
    String PRODUKT_TEIL = "produktTeil";
    String PROTOKOLL = "protokoll";
    String SONDERMODELL = "sondermodell";
    String SPURWEITE = "spurweite";
    String STEUERUNG = "steuerung";
    String UNTER_KATEGORIE = "unterKategorie";
    String VORBILD = "vorbild";
    String WAHRUNG = "wahrung";
    String ZUG = "zug";
    String ZUG_TYP = "zugTyp";
    String ZUG_CONSIST = "zugConsist";

    /** Fields */
    String ABBILDUNG = "abbildung";
    String ACHSFOLG_ID = "achsfolgId";
    String ANDERUNGSDATUM = "anderungsdatum";
    String ANDERUNGS_TYP = "anderungsTyp";
    String ANDERUNG_ID = "anderungId";
    String ADRESS = "adress";
    String ADRESS_ID = "adressId";
    String ADRESS_TYP = "adressTyp";
    String ANFAHRZUGKRAFT = "anfahrzugkraft";
    String ANLEITUNGEN = "anleitungen";
    String ANMERKUNG = "anmerkung";
    String ANTRIEB_ID = "antriebId";
    String ARTIKEL_ID = "artikelId";
    String AUFBAU_ID = "aufbauId";
    String AUSSERDIENST = "ausserdienst";
    String BAHNVERWALTUNG_ID = "bahnverwaltungId";
    String BAUZEIT = "bauzeit";
    String BELADUNG = "beladung";
    String BESTELL_NR = "bestell_nr";
    String BETREIBSNUMMER = "betreibsnummer";
    String BEZEICHNUNG = "bezeichnung";
    String CV = "cv";
    String CV_ID = "cvId";
    String DEZIMAL = "dezimal";
    String DECODER_ID = "decoderId";
    String DECODER_TYP_ID = "decoderTypId";
    String DELETED = "deleted";
    String DIENSTGEWICHT = "dienstgewicht";
    String DMLAUFRADHINTEN = "dmLaufradHinten";
    String DMLAUFRADVORN = "dmLaufradVorn";
    String DMTREIBRAD = "dmTreibrad";
    String DMZYLINDER = "dmZylinder";
    String DREHGESTELLBAUART = "drehgestellBauart";
    String END_YEAR = "endYear";
    String EPOCH_ID = "epochId";
    String EXPLOSIONSZEICHNUNG = "explosionszeichnung";
    String FAHRMOTOREN = "fahrmotoren";
    String FAHRSTUFE = "fahrstufe";
    String FUNKTION = "funktion";
    String FUNKTION_ID = "funktionId";
    String GATTUNG_ID = "gattungId";
    String GESCHWINDIGKEIT = "geschwindigkeit";
    String GROSSANSICHT = "grossansicht";
    String HERSTELLER_ID = "herstellerId";
    String I_MAX = "iMax";
    String ID = "id";
    String KAPAZITAT = "kapazitat";
    String KATEGORIE_ID = "kategorieId";
    String KAUFDATUM = "kaufdatum";
    String KESSELUBERDRUCK = "kesseluberdruck";
    String KLASSE = "klasse";
    String KOLBENHUB = "kolbenhub";
    String KONFIGURATION = "konfiguration";
    String KUPPLUNG_ID = "kupplungId";
    String LANGE = "lange";
    String LEISTUNG = "leistung";
    String LEISTUNGSUBERTRAGUNG = "leistungsubertragung";
    String LICHT_ID = "lichtId";
    String LINKS = "links";
    String MASSSTAB_ID = "massstabId";
    String MAXIMAL = "maximal";
    String MENGE = "menge";
    String MINIMAL = "minimal";
    String MITTELWAGEN = "mittelwagen";
    String MOTOR_TYP_ID = "motorTypId";
    String MOTORBAUART = "motorbauart";
    String NAME = "name";
    String POSITION = "position";
    String PREIS = "preis";
    String PRODUKT_ID = "produktId";
    String PROGRAMMABLE = "programmable";
    String PROTOKOLL_ID = "protokollId";
    String REICHWEITE = "reichweite";
    String REIHE = "reihe";
    String ROSTFLACHE = "rostflache";
    String SITZPLATZEKL1 = "sitzplatzeKl1";
    String SITZPLATZEKL2 = "sitzplatzeKl2";
    String SITZPLATZEKL3 = "sitzplatzeKl3";
    String SITZPLATZEKL4 = "sitzplatzeKl4";
    String SONDERMODELL_ID = "sondermodellId";
    String SOUND = "sound";
    String SPAN = "span";
    String SPURWEITE_ID = "spurweiteId";
    String START_YEAR = "startYear";
    String STATUS = "status";
    String STEUERUNG_ID = "steuerungId";
    String TEIL = "teil";
    String TEIL_ID = "teilId";
    String TELEFON = "telefon";
    String TRIEBKOPF = "triebkopf";
    String UBERHITZERFLACHE = "uberhitzerflache";
    String UNTER_KATEGORIE_ID = "unterKategorieId";
    String URL = "url";
    String VERBLEIBENDE = "verbleibende";
    String VERDAMPFUNG = "verdampfung";
    String VORBILD_ID = "vorbildId";
    String WAHRUNG_ID = "wahrungId";
    String WASSERVORRAT = "wasservorrat";
    String WERKSEINSTELLUNG = "werkseinstellung";
    String WERT = "wert";
    String ZUG_ID = "zugId";
    String ZUG_TYP_ID = "zugTypId";
    String ZYLINDER = "zylinder";

    String USER = "person";
    String PASSWORD = "password";
    String EMAIL = "email";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String ENABLED = "enabled";
    String LOGIN_ATTEMPTS = "login_attempts";
    String LOGIN_FAILURES = "login_failures";
    String PASSWORD_AGING = "password_aging";
    String PASSWORD_CHANGED = "password_changed";
    String LAST_LOGIN = "last_login";
    String CONFIRMATION_TOKEN = "confirmationToken";
    String CONFIRMATION_EXPIRES = "confirmationExpires";
    String LOCALE = "locale";
    String ROLES = "roles";

    String NAME_PATTERN = "[A-Z0-9\\-\\.]+";
    String ID_PATTERN = "[A-Z0-9]+";
    String FUNKTION_PATTERN = "(?:F\\d|F[12]\\d|F3[012]|K\\d|K1[012345]|S[0123456])";
}

