// module "types.js"
"use strict";

const ACHSFOLG_DROP = new DropDown(apiRoot() + "achsfolg", ["name"], ["name"]);
const ADRESS_TYP_DROP = new DropDown(apiRoot() + "enums/adressTyp", ["name"], ["name"]);
const ANTRIEB_DROP = new DropDown(apiRoot() + "antrieb", ["name"], ["name"]);
const ARTIKEL_DROP = new DropDown(apiRoot() + "artikel", ["name"], ["name"]);
const AUFBAU_DROP = new DropDown(apiRoot() + "aufbau", ["name"], ["name"]);
const BAHNVERWALTUNG_DROP = new DropDown(apiRoot() + "bahnverwaltung", ["name"], ["name"]);
const DECODER_DROP = new DropDown(apiRoot() + "decoder", ["name"], ["name"]);
const DECODER_TYP_DROP = new DropDown(apiRoot() + "decoderTyp", ["name"], ["name"]);
const EPOCH_DROP = new DropDown(apiRoot() + "epoch", ["name"], ["name"]);
const GATTUNG_DROP = new DropDown(apiRoot() + "gattung", ["name"], ["name"]);
const HERSTELLER_DROP = new DropDown(apiRoot() + "hersteller", ["name"], ["name"]);
const KATEGORIE_DROP = new DropDown(apiRoot() + "kategorie", ["name"], ["name"]);
const KONFIGURATION_DROP = new DropDown(apiRoot() + "enums/konfiguration", ["name"], ["name"]);
const KUPPLUNG_DROP = new DropDown(apiRoot() + "kupplung", ["name"], ["name"]);
const LAND_DROP = new DropDown(apiRoot() + "land", ["name"], ["name"]);
const LICHT_DROP = new DropDown(apiRoot() + "licht", ["name"], ["name"]);
const MASSSTAB_DROP = new DropDown(apiRoot() + "massstab", ["name"], ["name"]);
const MOTOR_TYP_DROP = new DropDown(apiRoot() + "motorTyp", ["name"], ["name"]);
const PRODUKT_DROP = new DropDown(apiRoot() + "produkt", ["name"], ["name"]);
const PROTOKOLL_DROP = new DropDown(apiRoot() + "protokoll", ["name"], ["name"]);
const SONDERMODELL_DROP = new DropDown(apiRoot() + "sondermodell", ["name"], ["name"]);
const SPURWEITE_DROP = new DropDown(apiRoot() + "spurweite", ["name"], ["name"]);
const STATUS_DROP = new DropDown(apiRoot() + "enums/status", ["name"], ["name"]);
const STECKER_DROP = new DropDown(apiRoot() + "enums/stecker", ["name"], ["name"]);
const STEUERUNG_DROP = new DropDown(apiRoot() + "steuerung", ["name"], ["name"]);
const UNTER_KATEGORIE_DROP = new DropDown(apiRoot() + "unterKategorie", ["name"], ["name"]);
const VORBILD_DROP = new DropDown(apiRoot() + "vorbild", ["name"], ["name"]);
const WAHRUNG_DROP = new DropDown(apiRoot() + "wahrung", ["name"], ["name"]);
const ZUG_TYP_DROP = new DropDown(apiRoot() + "zug_Typ", ["name"], ["name"]);

const ABBILDUNG = new IMGColumn("Abbildung", "abbildung", undefined, Editable.UPDATE, false);
const ACHSFOLG = new SelectColumn("Achsfolg", "achsfolg", ACHSFOLG_DROP, Editable.UPDATE, false);
const ADRESS_TYP = new SelectColumn("Typ", "adressTyp", ADRESS_TYP_DROP, Editable.UPDATE, true);
const ANLEITUNGEN = new PDFColumn("Anleitungen", "anleitungen", undefined, Editable.UPDATE, false);
const ANMERKUNG = new TextColumn("Anmerkung", "anmerkung", Editable.UPDATE, false, 30);
const ANTRIEB = new SelectColumn("Antrieb", "antrieb", ANTRIEB_DROP, Editable.UPDATE, false);
const ANZAHL = new NumberColumn("Anzahl", "anzahl", Editable.UPDATE, false, 300000, 1);
const ARTIKEL = new SelectColumn("Artikel", "artikel", ARTIKEL_DROP, Editable.UPDATE, false);
const AUFBAU = new SelectColumn("Aufbau", "aufbau", AUFBAU_DROP, Editable.UPDATE, false);
const AUSSERDIENST = new NumberColumn("Ausserdienst", "ausserdienst", Editable.UPDATE, false, 2100, 1800);
const BAHNVERWALTUNG = new SelectColumn("Bahnverwaltung", "bahnverwaltung", BAHNVERWALTUNG_DROP, Editable.UPDATE, false);
const BAUZEIT = new NumberColumn("Bauzeit", "bauzeit", Editable.UPDATE, false, 2100, 1800);
const BELADUNG = new TextColumn("Beladung", "beladung", Editable.UPDATE, false, 30);
const BESTELL_NR = new TextColumn("Bestell Nr", "bestellNr", Editable.ADD, true, 10);
const BETREIBSNUMMER = new TextColumn("Betreibsnummer", "betreibsnummer", Editable.UPDATE, false, 30);
const BEZEICHNUNG = new TextColumn("Bezeichnung", "bezeichnung", Editable.UPDATE, false, 50);
const CONSIST = new TextColumn("Consist", "consist", Editable.UPDATE, false, 30);
const CV = new NumberColumn("CV", "cv", Editable.ADD, true, 127, 1);
const DECIMALS = new NumberColumn("Decimals", "decimals", Editable.UPDATE, false, 3, 0);
const DECODER = new SelectColumn("Decoder", "decoder", DECODER_DROP, Editable.UPDATE, false);
const DECODER_TYP = new SelectColumn("Decoder Typ", "decoderTyp", DECODER_TYP_DROP, Editable.UPDATE, false);
const DELETED = new BoolColumn("deleted", "Deleted", Editable.UPDATE, false);
const DIENSTGEWICHT = new NumberColumn("Dienstgewicht", "dienstgewicht", Editable.UPDATE, false, 999, 1);
const DMLAUFRADHINTEN = new NumberColumn("DM Laufrad Hinten", "dmLaufradHinten", Editable.UPDATE, false, 3000, 1);
const DMLAUFRADVORN = new NumberColumn("DM Laufrad Vorn", "dmLaufradVorn", Editable.UPDATE, false, 3000, 1);
const DMTREIBRAD = new NumberColumn("DM Treibrad", "dmTreibrad", Editable.UPDATE, false, 3000, 1);
const DMZYLINDER = new NumberColumn("DM Zylinder", "dmZylinder", Editable.UPDATE, false, 3000, 1);
const DREHGESTELLBAUART = new TextColumn("Drehgestell Bauart", "drehgestellBauart", Editable.UPDATE, false, 30);
const EPOCH = new SelectColumn("Epoch", "epoch", EPOCH_DROP, Editable.UPDATE, false);
const EXPLOSIONSZEICHNUNG = new PDFColumn("Explosionszeichnung", "explosionszeichnung", undefined, Editable.UPDATE, false);
const FAHRMOTOREN = new NumberColumn("Fahrmotoren", "fahrmotoren", Editable.UPDATE, false, 5, 1);
const FAHRSTUFE = new NumberColumn("Fahrstufe", "fahrstufe", Editable.UPDATE, false, 127, 1);
const FUNKTION = new TextColumn("Funktion", "funktion", Editable.ADD, true, 3);
const GATTUNG = new SelectColumn("Gattung", "gattung", GATTUNG_DROP, Editable.UPDATE, false);
const GERAUSCH = new BoolColumn("Gerausch", "sound", Editable.UPDATE, true);
const GESCHWINDIGKEIT = new NumberColumn("Geschwindigkeit", "geschwindigkeit", Editable.UPDATE, false, 300, 1);
const HERSTELLER = new SelectColumn("Hersteller", "hersteller", HERSTELLER_DROP, Editable.ADD, true);
const I_MAX = new NumberColumn("I Max", "iMax", Editable.UPDATE, false, 1000, 1);
const INDEX = new NumberColumn("Index", "index", Editable.ADD, true, 3, 0);
const KAPAZITAT = new NumberColumn("Kapazitat", "kapazitat", Editable.UPDATE, false, 3000, 1, .01);
const KATEGORIE = new SelectColumn("Kategorie", "kategorie", KATEGORIE_DROP, Editable.UPDATE, false);
const KAUFDATUM = new DateColumn("Kaufdatum", "kaufdatum", Editable.UPDATE, false);
const KESSELUEBERDRUCK = new NumberColumn("Kesselueberdruck", "kesselueberdruck", Editable.UPDATE, false, 3000, 0, .01);
const KLASSE = new NumberColumn("Klasse", "klasse", Editable.UPDATE, false, 4, 0);
const KOLBENHUB = new NumberColumn("Kolbenhub", "kolbenhub", Editable.UPDATE, false, 3000, 1, .01);
const KONFIGURATION = new SelectColumn("Konfiguration", "konfiguration", KONFIGURATION_DROP, Editable.UPDATE, true);
const KUPPLUNG = new SelectColumn("Kupplung", "kupplung", KUPPLUNG_DROP, Editable.UPDATE, false);
const LAND = new SelectColumn("Land", "land", LAND_DROP, Editable.UPDATE, false);
const LANGE = new NumberColumn("Lange", "lange", Editable.UPDATE, false, 50, 1, .01);
const LEISTUNG = new NumberColumn("Leistung", "leistung", Editable.UPDATE, false, 10000, 0, .01);
const LEISTUNGSUEBERTRAGUNG = new NumberColumn("Leistungsuebertragung", "leistungsuebertragung", Editable.UPDATE, false, 10000, 0, .01);
const LICHT = new SelectColumn("Licht", "licht", LICHT_DROP, Editable.UPDATE, false);
const MASSSTAB = new SelectColumn("Massstab", "massstab", MASSSTAB_DROP, Editable.UPDATE, false);
const MAXIMAL = new NumberColumn("Maximal", "maximal", Editable.UPDATE, false, 30);
const MINIMAL = new NumberColumn("Minimal", "minimal", Editable.UPDATE, false, 30);
const MITTELWAGEN = new NumberColumn("Mittelwagen", "mittelwagen", Editable.UPDATE, false, 30, 0);
const MOTOR_TYP = new SelectColumn("MotorTyp", "motorTyp", MOTOR_TYP_DROP, Editable.UPDATE, false);
const MOTORBAUART = new TextColumn("Motorbauart", "motorbauart", Editable.UPDATE, false, 30);
const NAMEN = new TextColumn("Namen", "name", Editable.ADD, true, 30);
const POSITION = new NumberColumn("Position", "position", Editable.UPDATE, false, 30, 0);
const PREIS = new NumberColumn("Preis", "preis", Editable.UPDATE, false, undefined, 0, .01);
const PRODUKT = new SelectColumn("Produkt", "produkt", PRODUKT_DROP, Editable.UPDATE, false);
const PROGRAMMABLE = new BoolColumn("Programmable", "programmable", Editable.UPDATE, true);
const PROTOKOLL = new SelectColumn("Protokoll", "protokoll", PROTOKOLL_DROP, Editable.UPDATE, true);
const REICHWEITE = new NumberColumn("Reichweite", "reichweite", Editable.UPDATE, false, 3000, 0);
const REIHE = new NumberColumn("Reihe", "reihe", Editable.ADD, true, 1, 0);
const ROSTFLAECHE = new NumberColumn("Rostflaeche", "rostflaeche", Editable.UPDATE, false, 3000, 0, .01);
const SITZPLATZEKL1 = new NumberColumn("SitzplatzeKL1", "sitzplatzeKL1", Editable.UPDATE, false, 300, 0);
const SITZPLATZEKL2 = new NumberColumn("SitzplatzeKL2", "sitzplatzeKL2", Editable.UPDATE, false, 300, 0);
const SITZPLATZEKL3 = new NumberColumn("SitzplatzeKL3", "sitzplatzeKL3", Editable.UPDATE, false, 300, 0);
const SITZPLATZEKL4 = new NumberColumn("SitzplatzeKL4", "sitzplatzeKL4", Editable.UPDATE, false, 300, 0);
const SITZPLATZETZKL1 = new NumberColumn("SitzplatzeTZKL1", "sitzplatzeTZKL1", Editable.UPDATE, false, 300, 0);
const SITZPLATZETZKL2 = new NumberColumn("SitzplatzeTzKL2", "sitzplatzeTzKL2", Editable.UPDATE, false, 300, 0);
const SONDERMODELL = new SelectColumn("Sondermodell", "sondermodell", SONDERMODELL_DROP, Editable.UPDATE, false);
const SPAN = new NumberColumn("Span", "span", Editable.UPDATE, true, 16, 1);
const SPURWEITE = new SelectColumn("Spurweite", "spurweite", SPURWEITE_DROP, Editable.UPDATE, false);
const STATUS = new SelectColumn("Status", "status", STATUS_DROP, Editable.UPDATE, false);
const STECKER = new SelectColumn("Stecker", "stecker", STECKER_DROP, Editable.UPDATE, false);
const STEUERUNG = new SelectColumn("Steuerung", "steuerung", STEUERUNG_DROP, Editable.UPDATE, false);
const STUCK = new NumberColumn("Stuck", "stuck", Editable.UPDATE, false, 300, 0);
const TEIL = new NumberColumn("Teil", "teil", Editable.UPDATE, false, 300, 0);
const TELEFON = new PhoneColumn("Telefon", "telefon", Editable.UPDATE, false);
const TRIEBKOEPFE = new NumberColumn("Triebkoepfe", "triebkoepfe", Editable.UPDATE, false, 2, 0);
const TRIEBZUGANZEIGEN = new NumberColumn("TriebzugAnzeigen", "triebzugAnzeigen", Editable.UPDATE, false, 30);
const UEBERHITZERFLAECHE = new NumberColumn("Ueberhitzerflaeche", "ueberhitzerflaeche", Editable.UPDATE, false, 3000, 0, .01);
const UNTER_KATEGORIE = new SelectColumn("Unter Kategorie", "unterKategorie", UNTER_KATEGORIE_DROP, Editable.UPDATE, false);
const URL = new URLColumn("Url", "url", Editable.UPDATE, false);
const VERDAMPFUNG = new NumberColumn("Verdampfung", "verdampfung", Editable.UPDATE, false, 3000, 0, .01);
const VORBILD = new SelectColumn("Vorbild", "vorbild", VORBILD_DROP, Editable.UPDATE, false);
const WAHRUNG = new SelectColumn("Wahrung", "wahrung", WAHRUNG_DROP, Editable.UPDATE, false);
const WASSERVORRAT = new NumberColumn("Wasservorrat", "wasservorrat", Editable.UPDATE, false, 3000, 0, .01);
const WERKSEINSTELLUNG = new NumberColumn("Werkseinstellung", "werkseinstellung", Editable.UPDATE, true, 65535, 1);
const WERT = new NumberColumn("Wert", "wert", Editable.UPDATE, false, 65535, 1);
const ZUG = new TextColumn("Zug", "zug", Editable.UPDATE, false, 30);
const ZUG_TYP = new SelectColumn("Typ", "zug_Typ", ZUG_TYP_DROP, Editable.UPDATE, false);
const ZYLINDER = new NumberColumn("Zylinder", "zylinder", Editable.UPDATE, false, 100, 1);