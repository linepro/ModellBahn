package com.linepro.modellbahn.rest.util;

public interface ApiPaths {

    /** Parameter names */
    public static final String ID_PARAM_NAME = ApiNames.ID;

    public static final String NAME_PARAM_NAME = ApiNames.NAME;

    public static final String ARTIKEL_ID_PARAM_NAME = ApiNames.ARTIKEL_ID;

    public static final String ADRESS_TYP_PARAM_NAME = ApiNames.ADRESS_TYP;

    public static final String ADRESS_PARAM_NAME = ApiNames.ADRESS;

    public static final String BESTELL_NR_PARAM_NAME = ApiNames.BESTELL_NR;

    public static final String CV_PARAM_NAME = ApiNames.CV;

    public static final String DECODER_ID_PARAM_NAME = ApiNames.DECODER_ID;

    public static final String FUNKTION_PARAM_NAME = ApiNames.FUNKTION;  
    
    public static final String HERSTELLER_PARAM_NAME = ApiNames.HERSTELLER;

    public static final String KATEGORIE_PARAM_NAME = ApiNames.KATEGORIE;

    public static final String POSITION_PARAM_NAME = ApiNames.POSITION;

    public static final String PRODUKT_ID_PARAM_NAME = ApiNames.PRODUKT_ID;

    public static final String REIHE_PARAM_NAME = ApiNames.REIHE;

    public static final String TEIL_ID_PARAM_NAME = ApiNames.TEIL_ID;

    public static final String UNTER_KATEGORIE_PARAM_NAME = ApiNames.UNTER_KATEGORIE;

    public static final String ZUG_PARAM_NAME = ApiNames.ZUG;

    /** Root paths */

    public static final String API_ROOT = "/api/";

    public static final String WEB_ROOT = "/web";

    public static final String APPLICATION_WADL = "application.wadl";
    
    /** Service paths */
    public static final String ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG;
    
    public static final String ANTRIEB = API_ROOT + ApiNames.ANTRIEB;

    public static final String ARTIKEL = API_ROOT + ApiNames.ARTIKEL;

    public static final String AUFBAU = API_ROOT + ApiNames.AUFBAU;
    
    public static final String BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG;

    public static final String DECODER = API_ROOT + ApiNames.DECODER;
    
    public static final String DECODER_TYP = API_ROOT + ApiNames.DECODER_TYP;
    
    public static final String EPOCH = API_ROOT + ApiNames.EPOCH;
    
    public static final String GATTUNG = API_ROOT + ApiNames.GATTUNG;
    
    public static final String KATEGORIE = API_ROOT + ApiNames.KATEGORIE;
    
    public static final String KUPPLUNG = API_ROOT + ApiNames.KUPPLUNG;
    
    public static final String LICHT = API_ROOT + ApiNames.LICHT;
    
    public static final String MASSSTAB = API_ROOT + ApiNames.MASSSTAB;
    
    public static final String MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP;

    public static final String PRODUKT = API_ROOT + ApiNames.PRODUKT;

    public static final String PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL;
    
    public static final String SONDERMODEL = API_ROOT + ApiNames.SONDERMODEL;
    
    public static final String SPURWEITE = API_ROOT + ApiNames.SPURWEITE;
    
    public static final String STEUERUNG = API_ROOT + ApiNames.STEUERUNG;

    public static final String VORBILD = API_ROOT + ApiNames.VORBILD;

    public static final String WAHRUNG = API_ROOT + ApiNames.WAHRUNG;

    public static final String ZUG = API_ROOT + ApiNames.ZUG;

    public static final String ZUG_TYP = API_ROOT + ApiNames.ZUG_TYP;

    /** Function paths */
    public static final String FIELD_START = "/{";

    public static final String FIELD_END = "}";

    public static final String NOT_NULL_REGEX = ": [^//]+";

    public static final String NULL_REGEX = ": ^$";

    public static final String NUMBER_REGEX = ": \\d+";

    public static final String ID_PART = FIELD_START + ID_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String NAME_PART = FIELD_START + NAME_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String ADRESS_TYP_PART = FIELD_START + ADRESS_TYP_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String ADRESS_PART = FIELD_START + ADRESS_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String BESTELL_NR_PART = FIELD_START + BESTELL_NR_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String CV_PART = FIELD_START + CV_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String DECODER_PART = FIELD_START + DECODER_ID_PARAM_NAME + NUMBER_REGEX  + FIELD_END;

    public static final String FUNKTION_PART = FIELD_START + FUNKTION_PARAM_NAME + NOT_NULL_REGEX +FIELD_END;  

    public static final String HERSTELLER_PART = FIELD_START + HERSTELLER_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String KATEGORIE_PART = FIELD_START + KATEGORIE_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String POSITION_PATH = FIELD_START + POSITION_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String PRODUKT_PART = FIELD_START + PRODUKT_ID_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String REIHE_PART = FIELD_START + REIHE_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String TEIL_PART = FIELD_START + TEIL_ID_PARAM_NAME + NUMBER_REGEX + FIELD_END;

    public static final String UNTER_KATEGORIE_PART = FIELD_START + UNTER_KATEGORIE_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    public static final String ZUG_PART = FIELD_START + ZUG_PARAM_NAME + NOT_NULL_REGEX + FIELD_END;

    /** Parameterized child paths */
    public static final String DECODER_ADRESS_ROOT = DECODER_PART + "/" + ApiNames.ADRESS;
    public static final String DECODER_ADRESS_PATH = DECODER_ADRESS_ROOT + ADRESS_TYP_PART + "/" + ADRESS_PART;
    public static final String DECODER_ADRESS_LINK = "%s/" + ApiNames.ADRESS + "/%s/%d";  

    public static final String DECODER_CV_ROOT = DECODER_PART + "/" + ApiNames.CV;
    public static final String DECODER_CV_PATH = DECODER_CV_ROOT + CV_PART;
    public static final String DECODER_CV_LINK = "%s/" + ApiNames.CV + "/%d";  

    public static final String DECODER_FUNKTION_ROOT = DECODER_PART + "/" + ApiNames.FUNKTION;
    public static final String DECODER_FUNKTION_PATH = DECODER_FUNKTION_ROOT + REIHE_PART + FUNKTION_PART;
    public static final String DECODER_FUNKTION_LINK = "%s/" + ApiNames.FUNKTION + "/%d/%s";

    public static final String DECODER_TYP_PATH = HERSTELLER_PART + BESTELL_NR_PART;
    public static final String DECODER_TYP_LINK = "%s/%s";  

    public static final String DECODER_TYP_CV_ROOT = DECODER_TYP_PATH + "/" + ApiNames.CV;  
    public static final String DECODER_TYP_CV_PATH = DECODER_TYP_CV_ROOT + CV_PART;  
    public static final String DECODER_TYP_CV_LINK = "%s/" + ApiNames.CV + "/%d";  

    public static final String DECODER_TYP_FUNKTION_ROOT = DECODER_TYP_PATH + "/" + ApiNames.FUNKTION;  
    public static final String DECODER_TYP_FUNKTION_PATH = DECODER_TYP_FUNKTION_ROOT + REIHE_PART + FUNKTION_PART;  
    public static final String DECODER_TYP_FUNKTION_LINK = "%s/" + ApiNames.FUNKTION + "/%d/%s";
   
    public static final String UNTER_KATEGORIE_PATH = KATEGORIE_PART + UNTER_KATEGORIE_PART;
    public static final String UNTER_KATEGORIE_LINK = "%s/%s";

    public static final String PRODUKT_TEIL_PATH = PRODUKT_PART + TEIL_PART;
    public static final String PRODUKT_TEIL_LINK = "%s/%s";
    
    public static final String ZUG_CONSIST_PATH = ZUG_PART + POSITION_PATH;
    public static final String ZUG_CONSIST_LINK = "%s/%d";
}