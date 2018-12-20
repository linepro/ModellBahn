package com.linepro.modellbahn.rest.util;

public interface ApiPaths {

    String SEPARATOR = "/";

    String VERSION = "1.0.0";

    /** Parameter names */
    String ID_PARAM_NAME = ApiNames.ID;

    String NAME_PARAM_NAME = ApiNames.NAMEN;

    String ARTIKEL_ID_PARAM_NAME = ApiNames.ARTIKEL_ID;

    String ADRESS_TYP_PARAM_NAME = ApiNames.ADRESS_TYP;

    String ADRESS_PARAM_NAME = ApiNames.ADRESS;

    String BESTELL_NR_PARAM_NAME = ApiNames.BESTELL_NR;

    String CV_PARAM_NAME = ApiNames.CV;

    String DECODER_ID_PARAM_NAME = ApiNames.DECODER_ID;

    String FUNKTION_PARAM_NAME = ApiNames.FUNKTION;

    String HERSTELLER_PARAM_NAME = ApiNames.HERSTELLER;

    String INDEX_PARAM_NAME = ApiNames.INDEX;

    String KATEGORIE_PARAM_NAME = ApiNames.KATEGORIE;

    String POSITION_PARAM_NAME = ApiNames.POSITION;

    String PRODUKT_ID_PARAM_NAME = ApiNames.PRODUKT_ID;

    String REIHE_PARAM_NAME = ApiNames.REIHE;

    String TEIL_BESTELL_NR_PARAM_NAME = ApiNames.TEIL_BESTELL_NR;

    String TEIL_HERSTELLER_PARAM_NAME = ApiNames.TEIL_HERSTELLER;

    String UNTER_KATEGORIE_PARAM_NAME = ApiNames.UNTER_KATEGORIE;

    String ZUG_PARAM_NAME = ApiNames.ZUG;

    /** Root paths */

    String API_ROOT = SEPARATOR + "api" + SEPARATOR;

    String WEB_ROOT = SEPARATOR;

    String WEB_PART = "{path: .*}";

    String APPLICATION_WADL = "application.wadl";
    
    /** Service paths */
    String ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG;

    String ANTRIEB = API_ROOT + ApiNames.ANTRIEB;

    String ARTIKEL = API_ROOT + ApiNames.ARTIKEL;

    String AUFBAU = API_ROOT + ApiNames.AUFBAU;

    String BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG;

    String DECODER = API_ROOT + ApiNames.DECODER;

    String DECODER_TYP = API_ROOT + ApiNames.DECODER_TYP;
    
    String EPOCH = API_ROOT + ApiNames.EPOCH;

    String ENUMS = API_ROOT + ApiNames.ENUMS + SEPARATOR;

    String GATTUNG = API_ROOT + ApiNames.GATTUNG;

    String HERSTELLER = API_ROOT + ApiNames.HERSTELLER;
    
    String KATEGORIE = API_ROOT + ApiNames.KATEGORIE;
    
    String KUPPLUNG = API_ROOT + ApiNames.KUPPLUNG;
    
    String LAND = API_ROOT + ApiNames.LAND;
    
    String LICHT = API_ROOT + ApiNames.LICHT;
    
    String MASSSTAB = API_ROOT + ApiNames.MASSSTAB;
    
    String MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP;

    String PRODUKT = API_ROOT + ApiNames.PRODUKT;

    String PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL;
    
    String SONDERMODELL = API_ROOT + ApiNames.SONDERMODELL;
    
    String SPURWEITE = API_ROOT + ApiNames.SPURWEITE;
    
    String STEUERUNG = API_ROOT + ApiNames.STEUERUNG;

    String VORBILD = API_ROOT + ApiNames.VORBILD;

    String WAHRUNG = API_ROOT + ApiNames.WAHRUNG;

    String ZUG = API_ROOT + ApiNames.ZUG;

    String ZUG_TYP = API_ROOT + ApiNames.ZUG_TYP;

    /** Simple identifiers */
    String FIELD_START = SEPARATOR + "{";

    String FIELD_END = "}";

    String NOT_NULL_REGEX = ": [^//]+";

    String NULL_REGEX = ": ^$";

    String NUMBER_REGEX = ": \\d+";

    String ID_PART = FIELD_START + ID_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    String NAME_PART = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String ADRESS_TYP_PART = FIELD_START + ADRESS_TYP_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String ADRESS_PART = FIELD_START + ADRESS_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String BESTELL_NR_PART = FIELD_START + BESTELL_NR_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String CV_PART = FIELD_START + CV_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    String DECODER_PART = FIELD_START + DECODER_ID_PARAM_NAME + NUMBER_REGEX  + FIELD_END;

    String FUNKTION_PART = FIELD_START + FUNKTION_PARAM_NAME + NOT_NULL_REGEX +FIELD_END;

    String HERSTELLER_PART = FIELD_START + HERSTELLER_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String INDEX_PART = FIELD_START + INDEX_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    String KATEGORIE_PART = FIELD_START + KATEGORIE_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String POSITION_PART = FIELD_START + POSITION_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    String REIHE_PART = FIELD_START + REIHE_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    String TEIL_BESTELL_NR_PART = FIELD_START + TEIL_BESTELL_NR_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String TEIL_HERSTELLER_PART =  FIELD_START + TEIL_HERSTELLER_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String TEIL_PART = TEIL_HERSTELLER_PART + TEIL_BESTELL_NR_PART;

    String UNTER_KATEGORIE_PART = FIELD_START + UNTER_KATEGORIE_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String ZUG_PART = FIELD_START + ZUG_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    String ABBILDUNG_PART = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END + SEPARATOR + ApiNames.ABBILDUNG;

    /** Compound identifiers */
    String DECODER_TYP_PART = HERSTELLER_PART + BESTELL_NR_PART;
    String DECODER_TYP_LINK = "%s" + SEPARATOR + "%s";

    String PRODUKT_PART = HERSTELLER_PART + BESTELL_NR_PART;
    String PRODUKT_LINK = "%s" + SEPARATOR + "%s";

    /** Enum functions */
    String ENUMS_ADRESS_TYP_PATH = ApiNames.ADRESS_TYP;
    String ENUMS_STECKER_PATH = ApiNames.STECKER;
    String ENUMS_KONFIGURATION_PATH = ApiNames.KONFIGURATION;
    String ENUMS_STATUS_PATH = ApiNames.STATUS;

    /** Parameterized child paths */
    String DECODER_ADRESS_ROOT = DECODER_PART + SEPARATOR + ApiNames.ADRESSEN;
    String DECODER_ADRESS_PATH = DECODER_ADRESS_ROOT + INDEX_PART;
    String DECODER_ADRESS_LINK = "%s" + SEPARATOR + ApiNames.ADRESSEN + SEPARATOR + "%d";

    String DECODER_CV_ROOT = DECODER_PART + SEPARATOR + ApiNames.CVS;
    String DECODER_CV_PATH = DECODER_CV_ROOT + CV_PART;
    String DECODER_CV_LINK = "%s" + SEPARATOR + ApiNames.CVS + SEPARATOR + "%d";

    String DECODER_FUNKTION_ROOT = DECODER_PART + SEPARATOR + ApiNames.FUNKTIONEN;
    String DECODER_FUNKTION_PATH = DECODER_FUNKTION_ROOT + REIHE_PART + FUNKTION_PART;
    String DECODER_FUNKTION_LINK = "%s" + SEPARATOR + ApiNames.FUNKTIONEN + SEPARATOR + "%d" + SEPARATOR + "%s";

    String DECODER_TYP_PATH = DECODER_TYP_PART;

    String DECODER_TYP_ADRESS_ROOT = DECODER_TYP_PART + SEPARATOR + ApiNames.ADRESSEN;
    String DECODER_TYP_ADRESS_PATH = DECODER_TYP_ADRESS_ROOT + ADRESS_PART;
    String DECODER_TYP_ADRESS_LINK = "%s" + SEPARATOR + ApiNames.ADRESSEN + SEPARATOR + "%d";

    String DECODER_TYP_CV_ROOT = DECODER_TYP_PART + SEPARATOR + ApiNames.CVS;
    String DECODER_TYP_CV_PATH = DECODER_TYP_CV_ROOT + CV_PART;
    String DECODER_TYP_CV_LINK = "%s" + SEPARATOR + ApiNames.CVS + SEPARATOR + "%d";

    String DECODER_TYP_FUNKTION_ROOT = DECODER_TYP_PART + SEPARATOR + ApiNames.FUNKTIONEN;
    String DECODER_TYP_FUNKTION_PATH = DECODER_TYP_FUNKTION_ROOT + REIHE_PART + FUNKTION_PART;
    String DECODER_TYP_FUNKTION_LINK = "%s" + SEPARATOR + ApiNames.FUNKTIONEN + SEPARATOR + "%d" + SEPARATOR + "%s";

    String PRODUKT_ABBILDUNG = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END + SEPARATOR + ApiNames.ABBILDUNG;
    String PRODUKT_ANLEITUNGEN = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END + SEPARATOR + ApiNames.ANLEITUNGEN;
    String PRODUKT_EXPLOSIONSZEICHNUNG = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END + SEPARATOR + ApiNames.EXPLOSIONSZEICHNUNG;

    String PRODUKT_TEIL_ROOT = PRODUKT_PART + SEPARATOR + ApiNames.TEILEN;
    String PRODUKT_TEIL_PATH = PRODUKT_TEIL_ROOT + TEIL_PART;
    String PRODUKT_TEIL_LINK = "%s" + SEPARATOR + ApiNames.TEILEN + "%s" ;
    
    String UNTER_KATEGORIE_ROOT = KATEGORIE_PART + SEPARATOR + ApiNames.UNTER_KATEGORIEN;
    String UNTER_KATEGORIE_PATH = UNTER_KATEGORIE_ROOT + UNTER_KATEGORIE_PART;
    String UNTER_KATEGORIE_LINK = "%s" + SEPARATOR + ApiNames.UNTER_KATEGORIEN + SEPARATOR + "%s";

    String ZUG_CONSIST_ROOT = ZUG_PART + SEPARATOR + ApiNames.CONSIST;
    String ZUG_CONSIST_PATH = ZUG_CONSIST_ROOT + POSITION_PART;
    String ZUG_CONSIST_LINK = "%s" + SEPARATOR  + ApiNames.CONSIST + SEPARATOR + "%d";

    String SWAGGER = "swagger";
    String TYPE = "type";
    String SWAGGER_ROOT = API_ROOT + SWAGGER;
    String SWAGGER_RESOURCE = SEPARATOR + SWAGGER + ".{" + TYPE + ":json|yaml}";

    /* Multipart field names */
    String MULTIPART_FILE_DETAIL = "FileDetail";
    String MULTIPART_FILE_DATA = "FileData";
}