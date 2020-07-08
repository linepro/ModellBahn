package com.linepro.modellbahn.util.impexp;

import java.util.stream.Stream;

import org.thymeleaf.util.StringUtils;

import com.linepro.modellbahn.controller.impl.ApiNames;

public enum DataType {
    
    ACHSFOLG(ApiNames.ACHSFOLG),
    ANDERUNG(ApiNames.ANDERUNG),
    ANTRIEB(ApiNames.ANTRIEB),
    ARTIKEL(ApiNames.ARTIKEL),
    AUFBAU(ApiNames.AUFBAU),
    BAHNVERWALTUNG(ApiNames.BAHNVERWALTUNG),
    CONSIST(ApiNames.CONSIST, "ZugConsist"),
    DECODER(ApiNames.DECODER),
    DECODER_ADRESS(ApiNames.DECODER + "_" + ApiNames.ADRESS),
    DECODER_CV(ApiNames.DECODER + "_" + ApiNames.CV),
    DECODER_Funktion(ApiNames.DECODER + "_" + ApiNames.FUNKTION),
    DECODER_TYP(ApiNames.DECODER_TYP),
    DECODER_TYP_ADRESS(ApiNames.DECODER_TYP + "_" + ApiNames.ADRESS),
    DECODER_TYP_CV(ApiNames.DECODER_TYP + "_" + ApiNames.CV),
    DECODER_TYP_Funktion(ApiNames.DECODER_TYP + "_" + ApiNames.FUNKTION),
    EPOCH(ApiNames.EPOCH),
    GATTUNG(ApiNames.GATTUNG),
    HERSTELLER(ApiNames.HERSTELLER),
    KATEGORIE(ApiNames.KATEGORIE),
    UNTER_KATEGORIE(ApiNames.UNTER_KATEGORIE),
    KUPPLUNG(ApiNames.KUPPLUNG),
    LICHT(ApiNames.LICHT),
    MASSSTAB(ApiNames.MASSSTAB),
    MOTOR_TYP(ApiNames.MOTOR_TYP),
    PRODUKT(ApiNames.PRODUKT),
    PROTOKOLL(ApiNames.PROTOKOLL),
    SONDERMODELL(ApiNames.SONDERMODELL),
    SPURWEITE(ApiNames.SPURWEITE),
    TEIL(ApiNames.TEIL),
    VORBILD(ApiNames.VORBILD),
    ZUG(ApiNames.ZUG),
    ZUG_TYP(ApiNames.ZUG_TYP, "ZugTyp");

    private final String typeName;

    private final String beanPrefix;

    private DataType(String typeName) {
        this(StringUtils.capitalizeWords(typeName, "_").replaceAll("_", ""), StringUtils.capitalizeWords(typeName, "_").replaceAll("_", ""));
    }
    
    private DataType(String typeName, String beanPrefix) {
        this.typeName = StringUtils.unCapitalize(typeName);
        this.beanPrefix = beanPrefix; 
    }

    public String getTypeName() {
        return typeName;
    }

    public String getBeanPrefix() {
        return beanPrefix;
    }

    @Override
    public String toString() {
        return getTypeName();
    }
    
    public static DataType fromTypeName(String typeName) {
        return Stream.of(DataType.values())
                     .filter(v -> v.typeName.equals(typeName))
                     .findFirst()
                     .orElse(null);
    }
}
