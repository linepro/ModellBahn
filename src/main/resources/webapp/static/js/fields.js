// module 'fields.js'
'use strict';

const extractName = (entity) => { return entity.name };
const extractBezeichnung = (entity) => { return new DropOption( extractName(entity), entity.bezeichnung, entity.tooltip, entity.abbildung) };
const extractArtikelValue = (entity) => { return entity.artikelId };
const extractArtikelOption = (entity) => { return new DropOption( extractArtikelValue(entity), entity.bezeichnung) };
const extractDecoderValue = (entity) => { return entity.dcoderId };
const extractDecoderOption = (entity) => { return new DropOption( extractDecoderValue(entity), entity.bezeichnung) };
const extractKategorieValue = (entity) => { return entity.kategorie.name + '/' + entity.name };
const extractKategorieOption = (entity) => { return new DropOption( extractKategorieValue(entity), entity.kategorie.bezeichnung + ' - ' + entity.bezeichnung) };
const extractProduktValue = (entity) => { return entity.hersteller.name + '/' + entity.bestellNr };
const extractProduktOption = (entity) => { return new DropOption( extractProduktValue(entity), entity.hersteller.bezeichnung + ' - ' + entity.bestellNr) };
const extractVorbildValue = (entity) => { return entity.gattung.name };
const extractVorbildOption = (entity) => { return new DropOption(extractVorbildValue(entity), entity.bezeichnung) };

const ACHSFOLG_DROP = new DropDown(apiRoot() + 'achsfolg', extractBezeichnung);
const ADRESS_TYP_DROP = new DropDown(apiRoot() + 'enums/adressTyp', extractBezeichnung);
const ANDERUNGS_TYP_DROP = new DropDown(apiRoot() + 'enums/anderungsTyp', extractBezeichnung);
const ANTRIEB_DROP = new DropDown(apiRoot() + 'antrieb', extractBezeichnung);
const ARTIKEL_DROP = new DropDown(apiRoot() + 'artikel', extractArtikelOption);
const AUFBAU_DROP = new DropDown(apiRoot() + 'aufbau', extractBezeichnung);
const BAHNVERWALTUNG_DROP = new DropDown(apiRoot() + 'bahnverwaltung', extractBezeichnung);
const DECODER_DROP = new DropDown(apiRoot() + 'decoder', extractDecoderOption);
const DECODER_TYP_DROP = new DropDown(apiRoot() + 'decoderTyp', extractProduktOption);
const EPOCH_DROP = new DropDown(apiRoot() + 'epoch', extractBezeichnung);
const GATTUNG_DROP = new DropDown(apiRoot() + 'gattung', extractBezeichnung);
const HERSTELLER_DROP = new DropDown(apiRoot() + 'hersteller', extractBezeichnung);
const KONFIGURATION_DROP = new DropDown(apiRoot() + 'enums/konfiguration', extractBezeichnung);
const KUPPLUNG_DROP = new DropDown(apiRoot() + 'kupplung', extractBezeichnung);
const LEISTUNGSUBERTRAGUNG_DROP = new DropDown(apiRoot() + 'enums/leistungsubertragung', extractBezeichnung);
const LICHT_DROP = new DropDown(apiRoot() + 'licht', extractBezeichnung);
const MASSSTAB_DROP = new DropDown(apiRoot() + 'massstab', extractBezeichnung);
const MOTOR_TYP_DROP = new DropDown(apiRoot() + 'motorTyp', extractBezeichnung);
const PRODUKT_DROP = new DropDown(apiRoot() + 'produkt', extractProduktOption);
const PROTOKOLL_DROP = new DropDown(apiRoot() + 'protokoll', extractBezeichnung);
const SONDER_MODELL_DROP = new DropDown(apiRoot() + 'sonderModell', extractBezeichnung);
const SPURWEITE_DROP = new DropDown(apiRoot() + 'spurweite', extractBezeichnung);
const STATUS_DROP = new DropDown(apiRoot() + 'enums/status', extractBezeichnung);
const STECKER_DROP = new DropDown(apiRoot() + 'enums/stecker', extractBezeichnung);
const STEUERUNG_DROP = new DropDown(apiRoot() + 'steuerung', extractBezeichnung);
const UNTER_KATEGORIE_DROP = new DropDown(apiRoot() + 'kategorie/unterKategorien', extractKategorieOption);
const VORBILD_DROP = new DropDown(apiRoot() + 'vorbild', extractVorbildOption);
const WAHRUNG_DROP = new DropDown(apiRoot() + 'wahrung', extractBezeichnung);
const ZUG_TYP_DROP = new DropDown(apiRoot() + 'zugTyp', extractBezeichnung);

const artikelIdGetter = (entity) => { return entity.artikel ? entity.artikel.artikelId : undefined };
const artikelIdSetter = (entity, value) => { entity.artikel = { artikelId: value } };
const decoderIdGetter = (entity) => { return entity.decoder ? entity.decoder.decoderId : undefined };
const decoderIdSetter = (entity, value) => { entity.decoder = { decoderId: value } };
const decoderTypGetter = (entity) => { return entity.decoderTyp ? extractProduktValue(entity.decoderTyp) : undefined };
const decoderTypSetter = (entity, value) => { let parts = value.split('/'); entity.decoderTyp = { hersteller: { name: parts[0] }, bestellNr: parts[1] } };
const produktGetter = (entity) => { return entity.produkt ? extractProduktValue(entity.produkt) : undefined };
const produktSetter = (entity, value) => { let parts = value.split('/'); entity.produkt = { hersteller: { name: parts[0] }, bestellNr: parts[1] } };
const produktTeilGetter = (entity) => { return entity.teil ? extractProduktValue(entity.teil) : undefined };
const produktTeilSetter = (entity, value) => { let parts = value.split('/'); entity.teil = { hersteller: { name: parts[0] }, bestellNr: parts[1] } };
const unterKategorieGetter = (entity) => { return entity.unterKategorie ? extractKategorieValue(entity.unterKategorie) : undefined };
const unterKategorieSetter = (entity, value) => { let parts = value.split('/'); entity.unterKategorie = { kategorie: { name: parts[0] }, name: parts[1] } };
const vorbildGetter = (entity) => { return entity.vorbild ? entity.vorbild.gattung.name : undefined };
const vorbildSetter = (entity, value) => { entity.vorbild = { gattung: { name: value } } };

const defaultFieldGetter = (entity, fieldName) => { return entity[fieldName] };
const defaultFieldSetter = (entity, value, fieldName) => { entity[fieldName] = value };
const noOpSetter = (entity, value) => {};

const defaultNameGetter = (entity, fieldName) => { return entity[fieldName] ? entity[fieldName].name : undefined };
const defaultNameSetter = (entity, value, fieldName) => { return entity[fieldName] = { name: value } };

const ACHSFOLG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) => 
 { return new DropDownColumn('Achsfolg', 'achsfolg', getter, setter, ACHSFOLG_DROP, editable, required, 30, 5) };
const ADRESS_TYP_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) => 
 { return new DropDownColumn('Typ', 'adressTyp', getter, setter, ADRESS_TYP_DROP, editable, required, 5) };
const ANDERUNGS_TYP_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DropDownColumn('Typ', 'anderungsTyp', getter, setter, ANDERUNGS_TYP_DROP, editable, required) };
const ANTRIEB_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Antrieb', 'antrieb', getter, setter, ANTRIEB_DROP, editable, required, 30, 5) };
const ARTIKEL_SELECT = (editable, required, getter = artikelIdGetter, setter = artikelIdSetter) =>
 { return new DropDownColumn('Artikel', 'artikel', getter, setter, ARTIKEL_DROP, editable, required, 50, 5) };
const AUFBAU_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Aufbau', 'aufbau', getter, setter, AUFBAU_DROP, editable, required, 30, 5) };
const BAHNVERWALTUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Bahnverwaltung', 'bahnverwaltung', getter, setter, BAHNVERWALTUNG_DROP, editable, required, 30, 5) };
const DECODER_SELECT = (editable, required, getter = decoderIdGetter, setter = decoderIdSetter) =>
 { return new DropDownColumn('Decoder', 'decoder', getter, setter, DECODER_DROP, editable, required, 30, 5) };
const DECODER_TYP_SELECT = (editable, required, getter = decoderTypGetter, setter = decoderTypSetter) =>
 { return new DropDownColumn('Decoder Typ', 'decoderTyp', getter, setter, DECODER_TYP_DROP, editable, required, 50, 5) };
const EPOCH_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Epoch', 'epoch', getter, setter, EPOCH_DROP, editable, required, 30, 5) };
const GATTUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Gattung', 'gattung', getter, setter, GATTUNG_DROP, editable, required, 30, 5) };
const HERSTELLER_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Hersteller', 'hersteller', getter, setter, HERSTELLER_DROP, editable, required) };
const KONFIGURATION_SELECT = (editable, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DropDownColumn('Konfiguration', 'konfiguration', getter, setter, KONFIGURATION_DROP, editable, required) };
const KUPPLUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Kupplung', 'kupplung', getter, setter, KUPPLUNG_DROP, editable, required, 30, 5) };
const LEISTUNGSUBERTRAGUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DropDownColumn('Leistungsübertragung', 'leistungsubertragung', getter, setter, LEISTUNGSUBERTRAGUNG_DROP, editable, required, 30, 5) };
const LICHT_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Licht', 'licht', getter, setter, LICHT_DROP, editable, required, 30, 5) };
const MASSSTAB_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Maßstab', 'massstab', getter, setter, MASSSTAB_DROP, editable, required, 30, 5) };
const MOTOR_TYP_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('MotorTyp', 'motorTyp', getter, setter, MOTOR_TYP_DROP, editable, required, 30, 5) };
const PRODUKT_SELECT = (editable, required, getter = produktGetter, setter = produktSetter) =>
 { return new DropDownColumn('Produkt', 'produkt', getter, setter, PRODUKT_DROP, editable, required, 50, 5) };
const PROTOKOLL_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Protokoll', 'protokoll', getter, setter, PROTOKOLL_DROP, editable, required) };
const SONDER_MODELL_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Sonder Modell', 'sonderModell', getter, setter, SONDER_MODELL_DROP, editable, required, 30, 5) };
const SPURWEITE_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Spurweite', 'spurweite', getter, setter, SPURWEITE_DROP, editable, required, 30, 5) };
const STATUS_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DropDownColumn('Status', 'status', getter, setter, STATUS_DROP, editable, required, 30, 5) };
const STECKER_SELECT = (editable = Editable.UPDATE, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DropDownColumn('Stecker', 'stecker', getter, setter, STECKER_DROP, editable, required, 30, 5) };
const STEUERUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Steuerung', 'steuerung', getter, setter, STEUERUNG_DROP, editable, required, 30, 5) };
const TEIL_SELECT = (editable = Editable.UPDATE, required = false, getter = produktTeilGetter, setter = produktTeilSetter) =>
 { return new DropDownColumn('Teil', 'teil', getter, setter, PRODUKT_DROP, editable, required, 50, 5) };
const UNTER_KATEGORIE_SELECT = (editable = Editable.UPDATE, required = false, getter = unterKategorieGetter, setter = unterKategorieSetter) =>
 { return new DropDownColumn('Kategorie', 'unterKategorie', getter, setter, UNTER_KATEGORIE_DROP, editable, required) };
const VORBILD_SELECT = (editable = Editable.UPDATE, required = false, getter = vorbildGetter, setter = vorbildSetter) =>
 { return new DropDownColumn('Vorbild', 'vorbild', getter, setter, VORBILD_DROP, editable, required, 30, 5) };
const WAHRUNG_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Wahrung', 'wahrung', getter, setter, WAHRUNG_DROP, editable, required, 30, 5) };
const ZUG_TYP_SELECT = (editable = Editable.UPDATE, required = false, getter = defaultNameGetter, setter = defaultNameSetter) =>
 { return new DropDownColumn('Typ', 'typ', getter, setter, ZUG_TYP_DROP, editable, required, 30, 5) };

const ABBILDUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter) =>
 { return new IMGColumn('Abbildung', 'abbildung', getter, editable, required) };
const ADRESS = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn("Adress", "adress", getter, setter, editable, required, 65535, 1) };
const ANDERUNGS_DATUM = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DateColumn('Datum', 'anderungsDatum', getter, setter, editable, required, 5) };
const ANDERUNGS_ID = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Änderung', 'anderungId', getter, setter, editable, required, 30, 0) };
const ANLEITUNGEN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter) =>
 { return new PDFColumn('Anleitungen', 'anleitungen', getter, editable, required) };
const ANMERKUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Anmerkung', 'anmerkung', getter, setter, editable, required, 30) };
const ANFAHRZUGKRAFT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Anfahrzugkraft', 'anfahrzugkraft', getter, setter, editable, required, 300000, 1) };
const ANZAHL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Anzahl', 'anzahl', getter, setter, editable, required, 300000, 1) };
const ARTIKEL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Artikel', 'artikelId', getter, setter, editable, required, 5) };
const AUFBAU = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Aufbau', 'aufbau', getter, setter, editable, required, 5) };
const AUSSERDIENST = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DateColumn('Außerdienst', 'ausserdienst', getter, setter, editable, required) };
const BAUZEIT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DateColumn('Bauzeit', 'bauzeit', getter, setter, editable, required) };
const BELADUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Beladung', 'beladung', getter, setter, editable, required, 30) };
const BESTELL_NR = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Bestell Nr', 'bestellNr', getter, setter, editable, required, 10) };
const BETREIBSNUMMER = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Betreibsnummer', 'betreibsnummer', getter, setter, editable, required, 30) };
const BEZEICHNUNG = (editable = Editable.UPDATE, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Bezeichnung', 'bezeichnung', getter, setter, editable, required, 50) };
const CV = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('CV', 'cv', getter, setter, editable, required, 127, 1) };
const DEZIMAL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Dezimal', 'dezimal', getter, setter, editable, required, 3, 0) };
const DECODER = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Decoder', 'decoderId', getter, setter, editable, required, 5) };
const DELETED = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new BoolColumn('Deleted', 'deleted', getter, setter, editable, required) };
const DIENSTGEWICHT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Dienstgewicht', 'dienstgewicht', getter, setter, editable, required, 999, 1) };
const DMLAUFRADHINTEN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('DM Laufrad Hinten', 'dmLaufradHinten', getter, setter, editable, required, 3000, 1) };
const DMLAUFRADVORN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('DM Laufrad Vorn', 'dmLaufradVorn', getter, setter, editable, required, 3000, 1) };
const DMTREIBRAD = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('DM Treibrad', 'dmTreibrad', getter, setter, editable, required, 3000, 1) };
const DMZYLINDER = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('DM Zylinder', 'dmZylinder', getter, setter, editable, required, 3000, 1) };
const DREHGESTELLBAUART = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Drehgestell', 'drehgestellBauart', getter, setter, editable, required, 30) };
const EXPLOSIONSZEICHNUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter) =>
 { return new PDFColumn('Explosionszeichnung', 'explosionszeichnung', getter, editable, required) };
const FAHRMOTOREN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Fahrmotoren', 'fahrmotoren', getter, setter, editable, required, 5, 1) };
const FAHRSTUFE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Fahrstufe', 'fahrstufe', getter, setter, editable, required, 127, 1) };
const FUNKTION = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Funktion', 'funktion', getter, setter, editable, required, 3) };
const GERAUSCH = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new BoolColumn('Geräusch', 'gerausch', getter, setter, editable, required) };
const GESCHWINDIGKEIT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Geschwindigkeit', 'geschwindigkeit', getter, setter, editable, required, 300, 1) };
const HERSTELLER = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Hersteller', 'hersteller', getter, setter, editable, required, 3) };
const I_MAX = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('I Max', 'iMax', getter, setter, editable, required, 1000, 1) };
const INDEX = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Index', 'index', getter, setter, editable, required, 3, 0) };

const KAPAZITAT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Kapazität', 'kapazitat', getter, setter, editable, required, 3000, 1, 2) };
const KAUFDATUM = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new DateColumn('Kaufdatum', 'kaufdatum', getter, setter, editable, required) };
const KESSELUBERDRUCK = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Kesselüberdruck', 'kesseluberdruck', getter, setter, editable, required, 3000, 0, 2) };
const KLASSE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Klasse', 'klasse', getter, setter, editable, required, 4, 0) };
const KOLBENHUB = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Kolbenhub', 'kolbenhub', getter, setter, editable, required, 3000, 1, 2) };
const LANGE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Länge', 'lange', getter, setter, editable, required, 50, 1, 2) };
const LEISTUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Leistung', 'leistung', getter, setter, editable, required, 10000, 0, 2) };
const MAXIMAL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Maximal', 'maximal', getter, setter, editable, required, 30) };
const MINIMAL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Minimal', 'minimal', getter, setter, editable, required, 30) };
const MITTELWAGEN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Mittelwagen', 'mittelwagen', getter, setter, editable, required, 30, 0) };
const MOTORBAUART = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Motorbauart', 'motorbauart', getter, setter, editable, required, 30) };
const NAMEN = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Namen', 'name', getter, setter, editable, required, 30, '^[A-Z0-9.]+$') };
const POSITION = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Position', 'position', getter, setter, editable, required, 30, 0) };
const PREIS = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Preis', 'preis', getter, setter, editable, required, noOpSetter, 0, 2) };
const PROGRAMMABLE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new BoolColumn('Programmable', 'programmable', getter, setter, editable, required) };
const REICHWEITE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Reichweite', 'reichweite', getter, setter, editable, required, 3000, 0) };
const REIHE = (editable = Editable.ADD, required = true, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Reihe', 'reihe', getter, setter, editable, required, 1, 0) };
const ROSTFLACHE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Rostfläche', 'rostflache', getter, setter, editable, required, 3000, 0, 2) };
const SITZPLATZEKL1 = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Sitzplatze KL1', 'sitzplatzeKL1', getter, setter, editable, required, 300, 0) };
const SITZPLATZEKL2 = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Sitzplatze KL2', 'sitzplatzeKL2', getter, setter, editable, required, 300, 0) };
const SITZPLATZEKL3 = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Sitzplatze KL3', 'sitzplatzeKL3', getter, setter, editable, required, 300, 0) };
const SITZPLATZEKL4 = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Sitzplatze KL4', 'sitzplatzeKL4', getter, setter, editable, required, 300, 0) };
const SPAN = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Span', 'span', getter, setter, editable, required, 16, 1) };
const STUCK = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Stück', 'stuck', getter, setter, editable, required, 300, 0) };
const HERSTELLER_TELEFON = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new PhoneColumn('Telefon', 'telefon', getter, setter, editable, required) };
const TRIEBKOPF = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Triebköpfe', 'triebkopf', getter, setter, editable, required, 2, 0) };
const UBERHITZERFLACHE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Überhitzerfläche', 'uberhitzerflache', getter, setter, editable, required, 3000, 0, 2) };
const HERSTELLER_URL = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new URLColumn('Url', 'url', getter, setter, editable, required) };
const VERBLEIBENDE = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
{ return new NumberColumn('Verbleibende', 'verbleibende', getter, setter, editable, required, 300, 0) };
const VERDAMPFUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Verdampfung', 'verdampfung', getter, setter, editable, required, 3000, 0, 2) };
const WASSERVORRAT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Wasservorrat', 'wasservorrat', getter, setter, editable, required, 3000, 0, 2) };
const WERKSEINSTELLUNG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Werkseinstellung', 'werkseinstellung', getter, setter, editable, required, 65535, 1) };
const WERT = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Wert', 'wert', getter, setter, editable, required, 65535, 1) };
const ZUG = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new TextColumn('Zug', 'zug', getter, setter, editable, required, 30) };
const ZYLINDER = (editable = Editable.UPDATE, required = false, getter = defaultFieldGetter, setter = defaultFieldSetter) =>
 { return new NumberColumn('Zylinder', 'zylinder', getter, setter, editable, required, 100, 1) };

const artikelBezeichnungGetter = (entity) => { return entity.artikel.bezeichnung; };
const artikelProduktKategorieGetter = (entity) => { return unterKategorieGetter(entity.artikel.produkt); };
const artikelProduktLangeGetter = (entity) => { return entity.artikel.produkt.lange; };
const decoderCvBezeichnungGetter = (entity) => { return entity.cv.bezeichnung; };
const decoderCvCvGetter = (entity) => { return entity.cv.cv; };
const decoderCvWerkseinstellungGetter = (entity) => { return entity.cv.werkseinstellung; };
const decoderFunktionFunktionGetter = (entity) => { return entity.funktion.bezeichnung; };
const decoderFunktionProgrammableGetter = (entity) => { return entity.funktion.programmable; };
const decoderFunktionReiheGetter = (entity) => { return entity.funktion.reihe; };
const decoderTypBezeichnungGetter = (entity) => { return entity.decoderTyp.bezeichnung; };
const decoderTypIMaxGetter = (entity) => { return entity.decoderTyp.iMax; };
const decoderTypKonfigurationGetter = (entity) => { return entity.decoderTyp.konfiguration; };
const decoderTypSoundGetter = (entity) => { return entity.decoderTyp.sound; };