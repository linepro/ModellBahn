package com.linepro.modellbahn.rest.util;

public interface ApiPaths {

    /** Parameter names */
    public static final String CV_PARAM_NAME = "cv";

    public static final String DECODER_ID_PARAM_NAME = "decoderId";

    public static final String FN_PARAM_NAME = "fn";  

    public static final String ID_PARAM_NAME = "id";

    public static final String NAME_PARAM_NAME = "name";

    public static final String KATEGORIE_PARAM_NAME = "kategorie";

    public static final String REIHE_PARAM_NAME = "reihe";

    public static final String UNTER_KATEGORIE_PARAM_NAME = "unterKategorie";

    /** Root paths */
    public static final String API_ROOT = "/api";
    
    public static final String ACHSFOLG = API_ROOT + "achsfolg";
    
    public static final String ANTRIEB = API_ROOT + "antrieb";
    
    public static final String AUFBAU = API_ROOT + "aufbau";
    
    public static final String BAHNVERWALTUNG = API_ROOT + "bahnverwaltung";
    
    public static final String DECODER_TYP = API_ROOT + "decoderTyp";
    
    public static final String EPOCH = API_ROOT + "epochen";
    
    public static final String GATTUNG = API_ROOT + "gattung";
    
    public static final String KATEGORIE = API_ROOT + "kategorie";
    
    public static final String KUPPLUNG = API_ROOT + "kupplung";
    
    public static final String LICHT = API_ROOT + "licht";
    
    public static final String MASSSTAB = API_ROOT + "massstab";
    
    public static final String MOTOR_TYP = API_ROOT + "motorTyp";
    
    public static final String PROTOKOLL = API_ROOT + "protokoll";
    
    public static final String SONDERMODELL = API_ROOT + "sonderModell";
    
    public static final String SPURWEITE = API_ROOT + "spurweite";
    
    public static final String STEUERUNG = API_ROOT + "steuerung";
    
    public static final String WEB_ROOT = "/web";

    /** Parameterized paths */
    public static final String NUMBER_REGEX = ": [0-9]+";

    public static final String ID_PATH = "/{" + ID_PARAM_NAME + NUMBER_REGEX + "}";
    
    public static final String NAME_PATH = "/{" + NAME_PARAM_NAME + "}";

    public static final String DECODER_CV_PATH = "/{" + DECODER_ID_PARAM_NAME + "}/cv/{" + CV_PARAM_NAME + NUMBER_REGEX + "}";

    public static final String DECODER_FN_PATH = "/{" + DECODER_ID_PARAM_NAME + "}/fn/${" + REIHE_PARAM_NAME + "}/{" + FN_PARAM_NAME + "}";  

    public static final String DECODER_TYP_PATH = "/{hersteller}/{}";  

    public static final String DECODER_TYP_CV_PATH = DECODER_TYP_PATH + "/cv/{" + CV_PARAM_NAME + NUMBER_REGEX + "}";  

    public static final String DECODER_TYP_FN_PATH = DECODER_TYP_PATH + "/fn/{" + REIHE_PARAM_NAME + "}/{" + FN_PARAM_NAME + "}";  

    public static final String KATEGORIE_PATH = "/{" + KATEGORIE_PARAM_NAME + "}";

    public static final String UNTER_KATEGORIE_PATH = "/{" + KATEGORIE_PARAM_NAME + "}/{" + UNTER_KATEGORIE_PARAM_NAME + "}";
}