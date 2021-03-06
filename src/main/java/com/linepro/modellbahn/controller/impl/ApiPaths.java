package com.linepro.modellbahn.controller.impl;

import com.linepro.modellbahn.persistence.DBNames;

public interface ApiPaths {

    /** Root paths */
    String API_ROOT = "/api/";

    /** Service paths */
    String ADD_ROOT        = API_ROOT + ApiNames.ACHSFOLG;
    String ADD_ACHSFOLG    = ADD_ROOT;
    String GET_ACHSFOLG    = ADD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_ACHSFOLG = ADD_ROOT;
    String DELETE_ACHSFOLG = ADD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_ACHSFOLG = ADD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String ANTRIEB_ROOT   = API_ROOT + ApiNames.ANTRIEB;
    String ADD_ANTRIEB    = ANTRIEB_ROOT;
    String GET_ANTRIEB    = ANTRIEB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_ANTRIEB = ANTRIEB_ROOT;
    String DELETE_ANTRIEB = ANTRIEB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_ANTRIEB = ANTRIEB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String ARTIKEL_ROOT                = API_ROOT + ApiNames.ARTIKEL;
    String ADD_ARTIKEL                 = ARTIKEL_ROOT;
    String GET_ARTIKEL                 = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}";
    String SEARCH_ARTIKEL              = ARTIKEL_ROOT;
    String DELETE_ARTIKEL              = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}";
    String UPDATE_ARTIKEL              = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}";
    String ADD_ARTIKEL_ABBILDUNG       = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_ARTIKEL_ABBILDUNG    = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String ADD_ARTIKEL_GROSSANSICHT    = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.GROSSANSICHT;
    String DELETE_ARTIKEL_GROSSANSICHT = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.GROSSANSICHT;
    String ADD_ANDERUNG                = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ANDERUNGEN;
    String UPDATE_ANDERUNG             = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ANDERUNGEN + "/{" + ApiNames.ANDERUNG_ID + "}";
    String DELETE_ANDERUNG             = ARTIKEL_ROOT + "/{" + ApiNames.ARTIKEL_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ANDERUNGEN + "/{" + ApiNames.ANDERUNG_ID + "}";

    String AUFBAU_ROOT             = API_ROOT + ApiNames.AUFBAU;
    String ADD_AUFBAU              = AUFBAU_ROOT;
    String GET_AUFBAU              = AUFBAU_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_AUFBAU           = AUFBAU_ROOT;
    String DELETE_AUFBAU           = AUFBAU_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_AUFBAU           = AUFBAU_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_AUFBAU_ABBILDUNG    = AUFBAU_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_AUFBAU_ABBILDUNG = AUFBAU_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;

    String BAHNVERWALTUNG_ROOT   = API_ROOT + ApiNames.BAHNVERWALTUNG;
    String ADD_BAHNVERWALTUNG    = BAHNVERWALTUNG_ROOT;
    String GET_BAHNVERWALTUNG    = BAHNVERWALTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_BAHNVERWALTUNG = BAHNVERWALTUNG_ROOT;
    String DELETE_BAHNVERWALTUNG = BAHNVERWALTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_BAHNVERWALTUNG = BAHNVERWALTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String DECODER_ROOT            = API_ROOT + ApiNames.DECODER;
    String ADD_DECODER             = DECODER_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String GET_DECODER             = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}";
    String SEARCH_DECODER          = DECODER_ROOT;
    String DELETE_DECODER          = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}";
    String UPDATE_DECODER          = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}";
    String UPDATE_DECODER_ADRESS   = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.ADRESSEN + "/{" + ApiNames.INDEX + "}";
    String UPDATE_DECODER_CV       = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.CVS + "/{" + ApiNames.CV + "}";
    String UPDATE_DECODER_FUNKTION = DECODER_ROOT + "/{" + ApiNames.DECODER_ID + ":" + DBNames.ID_PATTERN + "}/" + ApiNames.FUNKTIONEN + "/{" + ApiNames.REIHE + "}/{" + ApiNames.FUNKTION + "}";

    String DECODER_TYP_ROOT               = API_ROOT + ApiNames.DECODER_TYP;
    String ADD_DECODER_TYP                = DECODER_TYP_ROOT;
    String GET_DECODER_TYP                = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_DECODER_TYP             = DECODER_TYP_ROOT;
    String DELETE_DECODER_TYP             = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_DECODER_TYP             = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_DECODER_TYP_ANLEITUNGEN    = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ANLEITUNGEN;
    String DELETE_DECODER_TYP_ANLEITUNGEN = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ANLEITUNGEN;
    String ADD_DECODER_TYP_ADRESS         = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ADRESSEN;
    String UPDATE_DECODER_TYP_ADRESS      = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ADRESSEN + "/{" + ApiNames.INDEX + "}";
    String DELETE_DECODER_TYP_ADRESS      = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ADRESSEN + "/{" + ApiNames.INDEX + "}";
    String ADD_DECODER_TYP_CV             = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.CVS;
    String UPDATE_DECODER_TYP_CV          = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.CVS + "/{" + ApiNames.CV + "}";
    String DELETE_DECODER_TYP_CV          = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.CVS + "/{" + ApiNames.CV + "}";
    String ADD_DECODER_TYP_FUNKTION       = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FUNKTIONEN;
    String UPDATE_DECODER_TYP_FUNKTION    = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FUNKTIONEN + "/{" + ApiNames.REIHE + "}/{" + ApiNames.FUNKTION + ":" + DBNames.FUNKTION_PATTERN + "}";
    String DELETE_DECODER_TYP_FUNKTION    = DECODER_TYP_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FUNKTIONEN + "/{" + ApiNames.REIHE + "}/{" + ApiNames.FUNKTION + ":" + DBNames.FUNKTION_PATTERN + "}";

    String EPOCH_ROOT   = API_ROOT + ApiNames.EPOCH;
    String ADD_EPOCH    = EPOCH_ROOT;
    String GET_EPOCH    = EPOCH_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_EPOCH = EPOCH_ROOT;
    String DELETE_EPOCH = EPOCH_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_EPOCH = EPOCH_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String ENUMS_ROOT                      = API_ROOT + ApiNames.ENUMS;
    String ENUMS_ADRESS_TYP_PATH           = ENUMS_ROOT + "/" + ApiNames.ADRESS_TYP;
    String ENUMS_ANDERUNGS_TYP_PATH        = ENUMS_ROOT + "/" + ApiNames.ANDERUNGS_TYP;
    String ENUMS_DECODER_STATUS_PATH       = ENUMS_ROOT + "/" + ApiNames.DECODER_STATUS;
    String ENUMS_KONFIGURATION_PATH        = ENUMS_ROOT + "/" + ApiNames.KONFIGURATION;
    String ENUMS_LAND_PATH                 = ENUMS_ROOT + "/" + ApiNames.LAND;
    String ENUMS_LEISTUNGSUBERTRAGUNG_PATH = ENUMS_ROOT + "/" + ApiNames.LEISTUNGSUBERTRAGUNG;
    String ENUMS_STATUS_PATH               = ENUMS_ROOT + "/" + ApiNames.STATUS;
    String ENUMS_STECKER_PATH              = ENUMS_ROOT + "/" + ApiNames.STECKER;
    String ENUMS_WAHRUNG_PATH              = ENUMS_ROOT + "/" + ApiNames.WAHRUNG;

    String GATTUNG_ROOT   = API_ROOT + ApiNames.GATTUNG;
    String ADD_GATTUNG    = GATTUNG_ROOT;
    String GET_GATTUNG    = GATTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_GATTUNG = GATTUNG_ROOT;
    String DELETE_GATTUNG = GATTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_GATTUNG = GATTUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String HERSTELLER_ROOT   = API_ROOT + ApiNames.HERSTELLER;
    String ADD_HERSTELLER    = HERSTELLER_ROOT;
    String GET_HERSTELLER    = HERSTELLER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_HERSTELLER = HERSTELLER_ROOT;
    String DELETE_HERSTELLER = HERSTELLER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_HERSTELLER = HERSTELLER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String KATEGORIE_ROOT         = API_ROOT + ApiNames.KATEGORIE;
    String ADD_KATEGORIE          = KATEGORIE_ROOT;
    String GET_KATEGORIE          = KATEGORIE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_KATEGORIE       = KATEGORIE_ROOT;
    String DELETE_KATEGORIE       = KATEGORIE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_KATEGORIE       = KATEGORIE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_UNTER_KATEGORIE    = KATEGORIE_ROOT + "/{" + ApiNames.KATEGORIE + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.UNTER_KATEGORIEN;
    String GET_UNTER_KATEGORIE    = KATEGORIE_ROOT + "/{" + ApiNames.KATEGORIE + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_UNTER_KATEGORIE = API_ROOT + ApiNames.UNTER_KATEGORIEN;
    String DELETE_UNTER_KATEGORIE = KATEGORIE_ROOT + "/{" + ApiNames.KATEGORIE + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_UNTER_KATEGORIE = KATEGORIE_ROOT + "/{" + ApiNames.KATEGORIE + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.UNTER_KATEGORIEN + "/{" + ApiNames.UNTER_KATEGORIE + ":" + DBNames.NAME_PATTERN + "}";

    String KUPPLUNG_ROOT             = API_ROOT + ApiNames.KUPPLUNG;
    String ADD_KUPPLUNG              = KUPPLUNG_ROOT;
    String GET_KUPPLUNG              = KUPPLUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_KUPPLUNG           = KUPPLUNG_ROOT;
    String DELETE_KUPPLUNG           = KUPPLUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_KUPPLUNG           = KUPPLUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_KUPPLUNG_ABBILDUNG    = KUPPLUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_KUPPLUNG_ABBILDUNG = KUPPLUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;

    String LICHT_ROOT             = API_ROOT + ApiNames.LICHT;
    String ADD_LICHT              = LICHT_ROOT;
    String GET_LICHT              = LICHT_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_LICHT           = LICHT_ROOT;
    String DELETE_LICHT           = LICHT_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_LICHT           = LICHT_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_LICHT_ABBILDUNG    = LICHT_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_LICHT_ABBILDUNG = LICHT_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;

    String MASSSTAB_ROOT   = API_ROOT + ApiNames.MASSSTAB;
    String ADD_MASSSTAB    = MASSSTAB_ROOT;
    String GET_MASSSTAB    = MASSSTAB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_MASSSTAB = MASSSTAB_ROOT;
    String DELETE_MASSSTAB = MASSSTAB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_MASSSTAB = MASSSTAB_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String MOTOR_TYP_ROOT   = API_ROOT + ApiNames.MOTOR_TYP;
    String ADD_MOTOR_TYP    = MOTOR_TYP_ROOT;
    String GET_MOTOR_TYP    = MOTOR_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_MOTOR_TYP = MOTOR_TYP_ROOT;
    String DELETE_MOTOR_TYP = MOTOR_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_MOTOR_TYP = MOTOR_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String PRODUKT_ROOT                       = API_ROOT + ApiNames.PRODUKT;
    String ADD_PRODUKT                        = PRODUKT_ROOT;
    String GET_PRODUKT                        = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_PRODUKT                     = PRODUKT_ROOT;
    String DELETE_PRODUKT                     = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_PRODUKT                     = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_PRODUKT_ABBILDUNG              = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_PRODUKT_ABBILDUNG           = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String ADD_PRODUKT_ANLEITUNGEN            = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ANLEITUNGEN;
    String DELETE_PRODUKT_ANLEITUNGEN         = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ANLEITUNGEN;
    String ADD_PRODUKT_EXPLOSIONSZEICHNUNG    = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.EXPLOSIONSZEICHNUNG;
    String DELETE_PRODUKT_EXPLOSIONSZEICHNUNG = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.EXPLOSIONSZEICHNUNG;
    String ADD_PRODUKT_GROSSANSICHT           = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.GROSSANSICHT;
    String DELETE_PRODUKT_GROSSANSICHT        = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.GROSSANSICHT;
    String ADD_PRODUKT_TEIL                   = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.TEILEN;
    String UPDATE_PRODUKT_TEIL                = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.TEILEN + "/{" + ApiNames.TEIL_HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.TEIL_BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";
    String DELETE_PRODUKT_TEIL                = PRODUKT_ROOT + "/{" + ApiNames.HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.TEILEN + "/{" + ApiNames.TEIL_HERSTELLER + ":" + DBNames.NAME_PATTERN + "}/{" + ApiNames.TEIL_BESTELL_NR + ":" + DBNames.NAME_PATTERN + "}";

    String PROTOKOLL_ROOT   = API_ROOT + ApiNames.PROTOKOLL;
    String ADD_PROTOKOLL    = PROTOKOLL_ROOT;
    String GET_PROTOKOLL    = PROTOKOLL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_PROTOKOLL = PROTOKOLL_ROOT;
    String DELETE_PROTOKOLL = PROTOKOLL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_PROTOKOLL = PROTOKOLL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String SONDERMODELL_ROOT   = API_ROOT + ApiNames.SONDERMODELL;
    String ADD_SONDERMODELL    = SONDERMODELL_ROOT;
    String GET_SONDERMODELL    = SONDERMODELL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_SONDERMODELL = SONDERMODELL_ROOT;
    String DELETE_SONDERMODELL = SONDERMODELL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_SONDERMODELL = SONDERMODELL_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String SPURWEITE_ROOT   = API_ROOT + ApiNames.SPURWEITE;
    String ADD_SPURWEITE    = SPURWEITE_ROOT;
    String GET_SPURWEITE    = SPURWEITE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_SPURWEITE = SPURWEITE_ROOT;
    String DELETE_SPURWEITE = SPURWEITE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_SPURWEITE = SPURWEITE_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String STEUERUNG_ROOT   = API_ROOT + ApiNames.STEUERUNG;
    String ADD_STEUERUNG    = STEUERUNG_ROOT;
    String GET_STEUERUNG    = STEUERUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_STEUERUNG = STEUERUNG_ROOT;
    String DELETE_STEUERUNG = STEUERUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_STEUERUNG = STEUERUNG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String VORBILD_ROOT             = API_ROOT + ApiNames.VORBILD;
    String ADD_VORBILD              = VORBILD_ROOT;
    String GET_VORBILD              = VORBILD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_VORBILD           = VORBILD_ROOT;
    String DELETE_VORBILD           = VORBILD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_VORBILD           = VORBILD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_VORBILD_ABBILDUNG    = VORBILD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;
    String DELETE_VORBILD_ABBILDUNG = VORBILD_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.ABBILDUNG;

    String ZUG_ROOT       = API_ROOT + ApiNames.ZUG;
    String ADD_ZUG        = ZUG_ROOT;
    String GET_ZUG        = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_ZUG     = ZUG_ROOT;
    String DELETE_ZUG     = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_ZUG     = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String ADD_CONSIST    = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FAHRZEUGEN;
    String DELETE_CONSIST = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FAHRZEUGEN + "/{" + ApiNames.POSITION + "}";
    String UPDATE_CONSIST = ZUG_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}/" + ApiNames.FAHRZEUGEN + "/{" + ApiNames.POSITION + "}";

    String ZUG_TYP_ROOT   = API_ROOT + ApiNames.ZUG_TYP;
    String ADD_ZUG_TYP    = ZUG_TYP_ROOT;
    String GET_ZUG_TYP    = ZUG_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String SEARCH_ZUG_TYP = ZUG_TYP_ROOT;
    String DELETE_ZUG_TYP = ZUG_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_ZUG_TYP = ZUG_TYP_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String DATA   = API_ROOT + ApiNames.DATA;
    String EXPORT = DATA + "/{" + ApiNames.DATA_TYPE + "}";
    String IMPORT = DATA + "/{" + ApiNames.DATA_TYPE + "}";

    /* Multipart field names */
    String MULTIPART_FILE_DETAIL = "FileData";
    String MULTIPART_FILE_DATA = "FileData";

    String[] API_ENDPOINTS = { API_ROOT + "**" };

    String USER_ROOT       = API_ROOT + "user";
    String SEARCH_USER     = USER_ROOT;
    String REGISTER_USER   = API_ROOT + "register";
    String CONFIRM_USER    = API_ROOT + "confirm";
    String FORGOT_PASSWORD = API_ROOT + "forgot";
    String RESET_PASSWORD  = API_ROOT + "reset";
    String GET_USER        = USER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String CHANGE_PASSWORD = USER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String UPDATE_USER     = USER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";
    String DELETE_USER     = USER_ROOT + "/{" + ApiNames.NAMEN + ":" + DBNames.NAME_PATTERN + "}";

    String[] REGISTER_ENDPOINTS = {
        REGISTER_USER, CONFIRM_USER, FORGOT_PASSWORD, RESET_PASSWORD
    };

    String[] USER_ENDPOINTS = {
        SEARCH_USER, GET_USER, CHANGE_PASSWORD, UPDATE_USER, DELETE_USER
    };

    String MANAGEMENT_ROOT = "/management/";
    String MANAGEMENT_HEALTH = MANAGEMENT_ROOT + "health";
    String MANAGEMENT_INDEX = MANAGEMENT_ROOT;
    String MANAGEMENT_INFO = MANAGEMENT_ROOT + "info";

    String[] MANAGEMENT_PUBLIC = {
        MANAGEMENT_INDEX, MANAGEMENT_HEALTH, MANAGEMENT_INFO
    };

    String[] MANAGEMENT_SECURED = {
       MANAGEMENT_ROOT + "**"
    };
}
