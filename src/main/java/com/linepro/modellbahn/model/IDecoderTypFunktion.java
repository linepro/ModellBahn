package com.linepro.modellbahn.model;

public interface IDecoderTypFunktion {

    IDecoderTyp getDecoderTyp();

    void setDecoderTyp(IDecoderTyp decoderTyp);

    Long getReihe();

    void setReihe(Long reihe);

    String getFunktionNr();

    void setFunktionNr(String funktionNr);

}