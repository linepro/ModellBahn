package com.linepro.modellbahn.rest.util;

public interface ApiMessages {
    String DOES_NOT_EXIST = "'%1$s' ist nicht vorhanden";
    String INVALID_FILE = "Ungültige Datei '%1$s'";
    String NOT_SUPPORTED = "'%1$s' wird nicht unterstützt";

    String ANDERUNG_DOES_NOT_EXIST = "Änderung '%1$s/" + ApiNames.ANDERUNG + "/%2$s' ist nicht vorhanden.";
    String ARTIKEL_DOES_NOT_EXIST = "Artikel '%1$s' ist nicht vorhanden.";
    String DECODER_ADRESS_DOES_NOT_EXIST = "Decoder Adress '%1$s/" + ApiNames.ADRESS + "/%2$d' ist nicht vorhanden.";
    String DECODER_CV_DOES_NOT_EXIST = "Decoder CV '%1$s/" + ApiNames.CV + "/%2$d' ist nicht vorhanden.";
    String DECODER_DOES_NOT_EXIST = "Decoder '%1$s/" + ApiNames.FUNKTION + "/%2$s' ist nicht vorhanden.";
    String DECODER_TYP_ADRESS_DECODER_TYP_FIXED = "Sie können den Decoder Typ eines Decoder Typ Adress nicht ändern, einen neue erstellen";
    String DECODER_TYP_ADRESS_DOES_NOT_EXIST = "Decoder Typ Adress '%1$s/%2$s/" + ApiNames.ADRESS + "/%3$d' ist nicht vorhanden.";
    String DECODER_TYP_CV_DECODER_TYP_FIXED = "Sie können den Decoder Typ eines Decoder Typ CV nicht ändern, einen neue erstellen";
    String DECODER_TYP_CV_DOES_NOT_EXIST = "Decoder Typ CV '%1$s/%2$s/" + ApiNames.CV + "/%3$d' ist nicht vorhanden.";
    String DECODER_TYP_DOES_NOT_EXIST = "Decoder Typ '%1$s' ist nicht vorhanden.";
    String DECODER_TYP_FUNKTION_DECODER_TYP_FIXED = "Sie können den Decoder Typ eines Decoder Typ Funktion nicht ändern, einen neue erstellen";
    String DECODER_TYP_FUNKTION_DOES_NOT_EXIST = "Decoder Typ Funktion '%1$s/%2$s/" + ApiNames.FUNKTION + "/%3$s' ist nicht vorhanden.";
    String KATEGORIE_DOES_NOT_EXIST = "Kategorie '%1$s' ist nicht vorhanden.";
    String PRODUKT_DOES_NOT_EXIST = "Produkt '%1$s/%2$s' ist nicht vorhanden.";
    String PRODUKT_TEIL_DOES_NOT_EXIST = "Produkt Teil '%1$s/%2$s/" + ApiNames.TEIL + "/%3$s/%4$s' ist nicht vorhanden.";
    String UNTER_KATEGORIE_DOES_NOT_EXIST = "Unter kategorie '%1$s/" + ApiNames.UNTER_KATEGORIE + "/%2$s' ist nicht vorhanden.";
    String UNTERKATEGORIE_KATEGORIE_FIXED = "Sie können die kategorie für eine unterkategorie nicht ändern, eine neue erstellen";
    String ZUG_CONSIST_DOES_NOT_EXIST = "Zug Consist '%1$s/" + ApiNames.CONSIST + "/%2$s' ist nicht vorhanden.";
    String ZUG_DOES_NOT_EXIST = "Zug '%1$s' ist nicht vorhanden.";
}
