package com.linepro.modellbahn.controller.impl;

public interface ApiPaths {

    /** Root paths */
    String API_ROOT = "/api/";

    /** Service paths */
    String ADD_ACHSFOLG    = API_ROOT + ApiNames.ACHSFOLG;
    String GET_ACHSFOLG    = API_ROOT + ApiNames.ACHSFOLG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG;
    String DELETE_ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG + "/{" + ApiNames.NAMEN + "}";

    String ADD_ANTRIEB    = API_ROOT + ApiNames.ANTRIEB;
    String GET_ANTRIEB    = API_ROOT + ApiNames.ANTRIEB + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_ANTRIEB = API_ROOT + ApiNames.ANTRIEB;
    String DELETE_ANTRIEB = API_ROOT + ApiNames.ANTRIEB + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_ANTRIEB = API_ROOT + ApiNames.ANTRIEB + "/{" + ApiNames.NAMEN + "}";

    String ADD_ARTIKEL              = API_ROOT + ApiNames.ARTIKEL;
    String GET_ARTIKEL              = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}";
    String SEARCH_ARTIKEL           = API_ROOT + ApiNames.ARTIKEL;
    String DELETE_ARTIKEL           = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}";
    String UPDATE_ARTIKEL           = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}";
    String ADD_ARTIKEL_ABBILDUNG    = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}/" + ApiNames.ABBILDUNG;
    String DELETE_ARTIKEL_ABBILDUNG = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}/" + ApiNames.ABBILDUNG;
    String ADD_ANDERUNG             = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}/" + ApiNames.ANDERUNGEN;
    String UPDATE_ANDERUNG          = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}/" + ApiNames.ANDERUNGEN + "/{" + ApiNames.ANDERUNG_ID + "}";
    String DELETE_ANDERUNG          = API_ROOT + ApiNames.ARTIKEL + "/{" + ApiNames.ARTIKEL_ID + "}/" + ApiNames.ANDERUNGEN + "/{" + ApiNames.ANDERUNG_ID + "}";

    String ADD_AUFBAU              = API_ROOT + ApiNames.AUFBAU;
    String GET_AUFBAU              = API_ROOT + ApiNames.AUFBAU + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_AUFBAU           = API_ROOT + ApiNames.AUFBAU;
    String DELETE_AUFBAU           = API_ROOT + ApiNames.AUFBAU + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_AUFBAU           = API_ROOT + ApiNames.AUFBAU + "/{" + ApiNames.NAMEN + "}";
    String ADD_AUFBAU_ABBILDUNG    = API_ROOT + ApiNames.AUFBAU + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_AUFBAU_ABBILDUNG = API_ROOT + ApiNames.AUFBAU + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;

    String ADD_BAHNVERWALTUNG    = API_ROOT + ApiNames.BAHNVERWALTUNG;
    String GET_BAHNVERWALTUNG    = API_ROOT + ApiNames.BAHNVERWALTUNG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG;
    String DELETE_BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG + "/{" + ApiNames.NAMEN + "}";

    String ADD_DECODER             = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String GET_DECODER             = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}";
    String SEARCH_DECODER          = API_ROOT + ApiNames.DECODER;
    String DELETE_DECODER          = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}";
    String UPDATE_DECODER          = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}";
    String UPDATE_DECODER_ADRESS   = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}/" + ApiNames.ADRESS + "/{" + ApiNames.INDEX + "}";
    String UPDATE_DECODER_CV       = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}/"  + ApiNames.CV + "/{" + ApiNames.CV + "}";
    String UPDATE_DECODER_FUNKTION = API_ROOT + ApiNames.DECODER + "/{" + ApiNames.DECODER_ID + "}/" + ApiNames.FUNKTION + "/{" + ApiNames.FUNKTION + "}/{" + ApiNames.REIHE + "}";

    String ADD_DECODER_TYP                = API_ROOT + ApiNames.DECODER_TYP;
    String GET_DECODER_TYP                = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String SEARCH_DECODER_TYP             = API_ROOT + ApiNames.DECODER_TYP;
    String DELETE_DECODER_TYP             = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String UPDATE_DECODER_TYP             = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String ADD_DECODER_TYP_ANLEITUNGEN    = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ANLEITUNGEN;
    String DELETE_DECODER_TYP_ANLEITUNGEN = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ANLEITUNGEN;
    String ADD_DECODER_TYP_ADRESS         = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ADRESS;
    String UPDATE_DECODER_TYP_ADRESS      = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ADRESS + "/{" + ApiNames.INDEX + "}";
    String DELETE_DECODER_TYP_ADRESS      = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ADRESS + "/{" + ApiNames.INDEX + "}";
    String ADD_DECODER_TYP_CV             = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.CV;
    String UPDATE_DECODER_TYP_CV          = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.CV + "/{" + ApiNames.CV + "}";
    String DELETE_DECODER_TYP_CV          = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.CV + "/{" + ApiNames.CV + "}";
    String ADD_DECODER_TYP_FUNKTION       = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.FUNKTION;
    String UPDATE_DECODER_TYP_FUNKTION    = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.FUNKTION + "/{" + ApiNames.FUNKTION + "}/{" + ApiNames.REIHE + "}";
    String DELETE_DECODER_TYP_FUNKTION    = API_ROOT + ApiNames.DECODER_TYP + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.FUNKTION + "/{" + ApiNames.FUNKTION + "}/{" + ApiNames.REIHE + "}";

    String ADD_EPOCH    = API_ROOT + ApiNames.EPOCH;
    String GET_EPOCH    = API_ROOT + ApiNames.EPOCH + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_EPOCH = API_ROOT + ApiNames.EPOCH;
    String DELETE_EPOCH = API_ROOT + ApiNames.EPOCH + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_EPOCH = API_ROOT + ApiNames.EPOCH + "/{" + ApiNames.NAMEN + "}";

    String ENUMS_ADRESS_TYP_PATH            = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.ADRESS_TYP;
    String ENUMS_ANDERUNGS_TYP_PATH         = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.ANDERUNGS_TYP;
    String ENUMS_DECODER_STATUS_PATH        = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.DECODER;
    String ENUMS_KONFIGURATION_PATH         = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.KONFIGURATION;
    String ENUMS_LEISTUNGS_UBERTRAGUNG_PATH = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.LEISTUNGSUBERTRAGUNG;
    String ENUMS_STATUS_PATH                = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.STATUS;
    String ENUMS_STECKER_PATH               = API_ROOT + ApiNames.ENUMS + "/" + ApiNames.STECKER;

    String ADD_GATTUNG    = API_ROOT + ApiNames.GATTUNG;
    String GET_GATTUNG    = API_ROOT + ApiNames.GATTUNG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_GATTUNG = API_ROOT + ApiNames.GATTUNG;
    String DELETE_GATTUNG = API_ROOT + ApiNames.GATTUNG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_GATTUNG = API_ROOT + ApiNames.GATTUNG + "/{" + ApiNames.NAMEN + "}";

    String ADD_HERSTELLER    = API_ROOT + ApiNames.HERSTELLER;
    String GET_HERSTELLER    = API_ROOT + ApiNames.HERSTELLER + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_HERSTELLER = API_ROOT + ApiNames.HERSTELLER;
    String DELETE_HERSTELLER = API_ROOT + ApiNames.HERSTELLER + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_HERSTELLER = API_ROOT + ApiNames.HERSTELLER + "/{" + ApiNames.NAMEN + "}";

    String ADD_KATEGORIE          = API_ROOT + ApiNames.KATEGORIE;
    String GET_KATEGORIE          = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_KATEGORIE       = API_ROOT + ApiNames.KATEGORIE;
    String DELETE_KATEGORIE       = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_KATEGORIE       = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.NAMEN + "}";
    String ADD_UNTER_KATEGORIE    = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.KATEGORIE + "}/" + ApiNames.UNTER_KATEGORIEN;
    String GET_UNTER_KATEGORIE    = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.KATEGORIE + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + "}";
    String SEARCH_UNTER_KATEGORIE = API_ROOT + ApiNames.UNTER_KATEGORIEN;
    String DELETE_UNTER_KATEGORIE = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.KATEGORIE + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + "}";
    String UPDATE_UNTER_KATEGORIE = API_ROOT + ApiNames.KATEGORIE + "/{" + ApiNames.KATEGORIE + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + "}";

    String ADD_KUPPLUNG              = API_ROOT + ApiNames.KUPPLUNG;
    String GET_KUPPLUNG              = API_ROOT + ApiNames.KUPPLUNG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_KUPPLUNG           = API_ROOT + ApiNames.KUPPLUNG;
    String DELETE_KUPPLUNG           = API_ROOT + ApiNames.KUPPLUNG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_KUPPLUNG           = API_ROOT + ApiNames.KUPPLUNG + "/{" + ApiNames.NAMEN + "}";
    String ADD_KUPPLUNG_ABBILDUNG    = API_ROOT + ApiNames.KUPPLUNG + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_KUPPLUNG_ABBILDUNG = API_ROOT + ApiNames.KUPPLUNG + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;

    String ADD_LICHT              = API_ROOT + ApiNames.LICHT;
    String GET_LICHT              = API_ROOT + ApiNames.LICHT + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_LICHT           = API_ROOT + ApiNames.LICHT;
    String DELETE_LICHT           = API_ROOT + ApiNames.LICHT + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_LICHT           = API_ROOT + ApiNames.LICHT + "/{" + ApiNames.NAMEN + "}";
    String ADD_LICHT_ABBILDUNG    = API_ROOT + ApiNames.LICHT + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_LICHT_ABBILDUNG = API_ROOT + ApiNames.LICHT + "/{" + ApiNames.NAMEN + "}/" + ApiNames.ABBILDUNG;

    String ADD_MASSSTAB    = API_ROOT + ApiNames.MASSSTAB;
    String GET_MASSSTAB    = API_ROOT + ApiNames.MASSSTAB + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_MASSSTAB = API_ROOT + ApiNames.MASSSTAB;
    String DELETE_MASSSTAB = API_ROOT + ApiNames.MASSSTAB + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_MASSSTAB = API_ROOT + ApiNames.MASSSTAB + "/{" + ApiNames.NAMEN + "}";

    String ADD_MOTOR_TYP    = API_ROOT + ApiNames.MOTOR_TYP;
    String GET_MOTOR_TYP    = API_ROOT + ApiNames.MOTOR_TYP + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP;
    String DELETE_MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP + "/{" + ApiNames.NAMEN + "}";

    String ADD_PRODUKT                        = API_ROOT + ApiNames.PRODUKT;
    String GET_PRODUKT                        = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String SEARCH_PRODUKT                     = API_ROOT + ApiNames.PRODUKT;
    String DELETE_PRODUKT                     = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String UPDATE_PRODUKT                     = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}";
    String ADD_PRODUKT_ABBILDUNG              = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ABBILDUNG;
    String DELETE_PRODUKT_ABBILDUNG           = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ABBILDUNG;
    String ADD_PRODUKT_ANLEITUNGEN            = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ANLEITUNGEN;
    String DELETE_PRODUKT_ANLEITUNGEN         = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.ANLEITUNGEN;
    String ADD_PRODUKT_EXPLOSIONSZEICHNUNG    = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.EXPLOSIONSZEICHNUNG;
    String DELETE_PRODUKT_EXPLOSIONSZEICHNUNG = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.EXPLOSIONSZEICHNUNG;
    String ADD_PRODUKT_TEIL                   = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.TEILEN;
    String UPDATE_PRODUKT_TEIL                = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.TEILEN + "/{" + ApiNames.TEIL_HERSTELLER + "}/{" + ApiNames.TEIL_BESTELL_NR + "}";
    String DELETE_PRODUKT_TEIL                = API_ROOT + ApiNames.PRODUKT + "/{" + ApiNames.HERSTELLER + "}/{" + ApiNames.BESTELL_NR + "}/" + ApiNames.TEILEN + "/{" + ApiNames.TEIL_HERSTELLER + "}/{" + ApiNames.TEIL_BESTELL_NR + "}";

    String ADD_PROTOKOLL    = API_ROOT + ApiNames.PROTOKOLL;
    String GET_PROTOKOLL    = API_ROOT + ApiNames.PROTOKOLL + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL;
    String DELETE_PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL + "/{" + ApiNames.NAMEN + "}";

    String ADD_SONDERMODELL    = API_ROOT + ApiNames.SONDERMODELL;
    String GET_SONDERMODELL    = API_ROOT + ApiNames.SONDERMODELL + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_SONDERMODELL = API_ROOT + ApiNames.SONDERMODELL;
    String DELETE_SONDERMODELL = API_ROOT + ApiNames.SONDERMODELL + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_SONDERMODELL = API_ROOT + ApiNames.SONDERMODELL + "/{" + ApiNames.NAMEN + "}";

    String ADD_SPURWEITE    = API_ROOT + ApiNames.SPURWEITE;
    String GET_SPURWEITE    = API_ROOT + ApiNames.SPURWEITE + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_SPURWEITE = API_ROOT + ApiNames.SPURWEITE;
    String DELETE_SPURWEITE = API_ROOT + ApiNames.SPURWEITE + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_SPURWEITE = API_ROOT + ApiNames.SPURWEITE + "/{" + ApiNames.NAMEN + "}";

    String ADD_STEUERUNG    = API_ROOT + ApiNames.STEUERUNG;
    String GET_STEUERUNG    = API_ROOT + ApiNames.STEUERUNG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_STEUERUNG = API_ROOT + ApiNames.STEUERUNG;
    String DELETE_STEUERUNG = API_ROOT + ApiNames.STEUERUNG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_STEUERUNG = API_ROOT + ApiNames.STEUERUNG + "/{" + ApiNames.NAMEN + "}";

    String ADD_VORBILD              = API_ROOT + ApiNames.VORBILD;
    String GET_VORBILD              = API_ROOT + ApiNames.VORBILD + "/{" + ApiNames.NAMEN + ":[A-Za-z0-9 \\.]+}";
    String SEARCH_VORBILD           = API_ROOT + ApiNames.VORBILD;
    String DELETE_VORBILD           = API_ROOT + ApiNames.VORBILD + "/{" + ApiNames.NAMEN + ":[A-Za-z0-9 \\\\.]+}";
    String UPDATE_VORBILD           = API_ROOT + ApiNames.VORBILD + "/{" + ApiNames.NAMEN + ":[A-Za-z0-9 \\\\.]+}";
    String ADD_VORBILD_ABBILDUNG    = API_ROOT + ApiNames.VORBILD + "/{" + ApiNames.NAMEN + ":[A-Za-z0-9 \\\\.]+}/" + ApiNames.ABBILDUNG;
    String DELETE_VORBILD_ABBILDUNG = API_ROOT + ApiNames.VORBILD + "/{" + ApiNames.NAMEN + ":[A-Za-z0-9 \\\\.]+}/" + ApiNames.ABBILDUNG;

    String ADD_ZUG        = API_ROOT + ApiNames.ZUG;
    String GET_ZUG        = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_ZUG     = API_ROOT + ApiNames.ZUG;
    String DELETE_ZUG     = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_ZUG     = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}";
    String ADD_CONSIST    = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}/" + ApiNames.CONSIST;
    String DELETE_CONSIST = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}/" + ApiNames.CONSIST + "/{" + ApiNames.POSITION + "}";
    String UPDATE_CONSIST = API_ROOT + ApiNames.ZUG + "/{" + ApiNames.NAMEN + "}/" + ApiNames.CONSIST + "/{" + ApiNames.POSITION + "}";

    String ADD_ZUG_TYP    = API_ROOT + ApiNames.ZUG_TYP;
    String GET_ZUG_TYP    = API_ROOT + ApiNames.ZUG_TYP + "/{" + ApiNames.NAMEN + "}";
    String SEARCH_ZUG_TYP = API_ROOT + ApiNames.ZUG_TYP;
    String DELETE_ZUG_TYP = API_ROOT + ApiNames.ZUG_TYP + "/{" + ApiNames.NAMEN + "}";
    String UPDATE_ZUG_TYP = API_ROOT + ApiNames.ZUG_TYP + "/{" + ApiNames.NAMEN + "}";

    String DATA   = API_ROOT + ApiNames.DATA;
    String EXPORT = DATA + "/{" + ApiNames.DATA_TYPE + "}";
    String IMPORT = DATA + "/{" + ApiNames.DATA_TYPE + "}";

    /* Multipart field names */
    String MULTIPART_FILE_DETAIL = "FileData";
    String MULTIPART_FILE_DATA = "FileData";
    
    String[] API_ENDPOINTS = { API_ROOT + "/**" };

}
