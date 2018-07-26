package com.linepro.modellbahn.rest.util;

public interface ApiPaths {

    /** Parameter names */
    public static final String ARTIKEL_ID_PARAM_NAME = ApiNames.ARTIKEL_ID;

    public static final String CV_PARAM_NAME = ApiNames.CV;

    public static final String DECODER_ID_PARAM_NAME = ApiNames.DECODER_ID;

    public static final String FN_PARAM_NAME = ApiNames.FUNKTION;  

    public static final String ID_PARAM_NAME = ApiNames.ID;

    public static final String NAME_PARAM_NAME = ApiNames.NAME;

    public static final String KATEGORIE_PARAM_NAME = ApiNames.KATEGORIE;

    public static final String PRODUKT_ID_PARAM_NAME = ApiNames.PRODUKT_ID;

    public static final String REIHE_PARAM_NAME = ApiNames.REIHE;

    public static final String TEIL_ID_PARAM_NAME = ApiNames.TEIL_ID;

    public static final String UNTER_KATEGORIE_PARAM_NAME = ApiNames.UNTER_KATEGORIE;
    
    public static final String ZUG_PARAM_NAME = ApiNames.ZUG;

    /** Root paths */
    public static final String API_ROOT = "/api/";
    
    public static final String ACHSFOLG = API_ROOT + ApiNames.ACHSFOLG;
    
    public static final String ANTRIEB = API_ROOT + ApiNames.ANTRIEB;

    public static final String APPLICATION_WADL = "application.wadl";
    
    public static final String AUFBAU = API_ROOT + ApiNames.AUFBAU;
    
    public static final String BAHNVERWALTUNG = API_ROOT + ApiNames.BAHNVERWALTUNG;

    public static final String BESTELL_NR_PARAM_NAME = ApiNames.BESTELL_NR;  
   
    public static final String DECODER_TYP = API_ROOT + ApiNames.DECODER_TYP;
    
    public static final String EPOCH = API_ROOT + ApiNames.EPOCH;
    
    public static final String GATTUNG = API_ROOT + ApiNames.GATTUNG;
    
    public static final String HERSTELLER_PARAM_NAME = ApiNames.HERSTELLER;
    
    public static final String KATEGORIE = API_ROOT + ApiNames.KATEGORIE;
    
    public static final String KUPPLUNG = API_ROOT + ApiNames.KUPPLUNG;
    
    public static final String LICHT = API_ROOT + ApiNames.LICHT;
    
    public static final String MASSSTAB = API_ROOT + ApiNames.MASSSTAB;
    
    public static final String MOTOR_TYP = API_ROOT + ApiNames.MOTOR_TYP;

    public static final String POSITION_PARAM_NAME = ApiNames.POSITION;
    
    public static final String PROTOKOLL = API_ROOT + ApiNames.PROTOKOLL;
    
    public static final String SONDERMODEL = API_ROOT + ApiNames.SONDERMODEL;
    
    public static final String SPURWEITE = API_ROOT + ApiNames.SPURWEITE;
    
    public static final String STEUERUNG = API_ROOT + ApiNames.STEUERUNG;
    
    public static final String WEB_ROOT = "/web/";

    /** Parameterized paths */
    public static final String NUMBER_REGEX = ": [0-9]+";

    public static final String ID_PATH = "/{" + ID_PARAM_NAME + NUMBER_REGEX + "}";
    
    public static final String NAME_PATH = "/{" + NAME_PARAM_NAME + "}";

    public static final String DECODER_PATH = "/{" + DECODER_ID_PARAM_NAME + "}";

    public static final String DECODER_CV_PATH = DECODER_PATH + "/" + ApiNames.CV + "/{" + CV_PARAM_NAME + NUMBER_REGEX + "}";
    public static final String DECODER_CV_LINK = "%s/" + ApiNames.CV + "/%d";  

    public static final String DECODER_FN_PATH = DECODER_PATH + "/" + ApiNames.FUNKTION + "/{" + REIHE_PARAM_NAME + "}/{" + FN_PARAM_NAME + "}";
    public static final String DECODER_FN_LINK = "%s/" + ApiNames.FUNKTION + "/%d/%s";  

    public static final String DECODER_TYP_PATH = "/{" + HERSTELLER_PARAM_NAME + "}/{" + BESTELL_NR_PARAM_NAME + "}";  
    public static final String DECODER_TYP_LINK = "%s/%s";  

    public static final String DECODER_TYP_CV_PATH = DECODER_TYP_PATH + "/" + ApiNames.CV + "/{" + CV_PARAM_NAME + NUMBER_REGEX + "}";  
    public static final String DECODER_TYP_CV_LINK = "%s/" + ApiNames.CV + "/%s";  

    public static final String DECODER_TYP_FN_PATH = DECODER_TYP_PATH + "/" + ApiNames.FUNKTION + "/{" + REIHE_PARAM_NAME + "}/{" + FN_PARAM_NAME + "}";  
    public static final String DECODER_TYP_FN_LINK = "%s/" + ApiNames.FUNKTION + "/%d/%s";  

    public static final String KATEGORIE_PATH = "/{" + KATEGORIE_PARAM_NAME + "}";
    
    public static final String UNTER_KATEGORIE_PATH = "/{" + KATEGORIE_PARAM_NAME + "}/{" + UNTER_KATEGORIE_PARAM_NAME + "}";
    public static final String UNTER_KATEGORIE_LINK = "%s/%s";

    public static final String PRODUKT_TEIL_PATH = "/{" + PRODUKT_ID_PARAM_NAME + "}/{" + TEIL_ID_PARAM_NAME + "}";
    public static final String PRODUKT_TEIL_LINK = "%s/%s";
    
    public static final String ZUG_CONSIST_PATH = "/{" + ZUG_PARAM_NAME + "}/{" + POSITION_PARAM_NAME + "}";
    public static final String ZUG_CONSIST_LINK = "%s/%d";
}