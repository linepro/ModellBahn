package com.linepro.modellbahn.model;

public interface IDecoderTypFunktion extends INamedItem {

    IDecoderTyp getDecoderTyp();

    void setDecoderTyp(IDecoderTyp decoderTyp);

    Integer getReihe();

    void setReihe(Integer reihe);

    Boolean getProgrammable();
    
    void setProgrammable(Boolean programmable);
}