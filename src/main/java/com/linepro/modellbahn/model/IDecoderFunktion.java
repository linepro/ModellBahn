package com.linepro.modellbahn.model;

public interface IDecoderFunktion {

    IDecoder getDecoder();

    void setDecoder(IDecoder decoder);

    IDecoderTypFunktion getFunktion();

    void setFunktion(IDecoderTypFunktion funktion);

    String getWert();

    void setWert(String wert);

}
