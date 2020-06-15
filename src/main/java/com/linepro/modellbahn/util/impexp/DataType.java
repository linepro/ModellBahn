package com.linepro.modellbahn.util.impexp;

public enum DataType {
    
    ACHSFOLG("Achsfolg"),
    ANDERUNG("Anderung"),
    ANTRIEB("Antrieb"),
    ARTIKEL("Artikel"),
    AUFBAU("Aufbau"),
    BAHNVERWALTUNG("Bahnverwaltung"),
    CONSIST("Consist"),
    DECODER("Decoder"),
    DECODER_ADRESS("DecoderAdress"),
    DECODER_CV("DecoderCV"),
    DECODER_Funktion("Decoder_Funktion"),
    DECODER_TYP("DecoderTyp"),
    DECODER_TYP_ADRESS("DecoderTypAdress"),
    DECODER_TYP_CV("DecoderTypCV"),
    DECODER_TYP_Funktion("DecoderTypFunktion"),
    EPOCH("Epoch"),
    GATTUNG("Gattung"),
    HERSTELLER("hersteller"),
    KATEGORIE("Kategorie"),
    UNTER_KATEGORIE("UnterKategorie"),
    KUPPLUNG("Kupplung"),
    LICHT("Licht"),
    MASSSTAB("Massstab"),
    MOTOR_TYP("MotorTyp"),
    PRODUKT("Produkt"),
    PROTOKOLL("Protokoll"),
    SONDERMODELL("Sondermodell"),
    SPURWEITE("Spurweite"),
    TEIL("Teil"),
    VORBILD("Vorbild"),
    ZUG("Zug"),
    ZUG_TYP("ZugTyp");

    private final String description;

    private DataType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
    
}
