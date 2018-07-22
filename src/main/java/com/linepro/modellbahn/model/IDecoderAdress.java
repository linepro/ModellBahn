package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.util.AdressTyp;

public interface IDecoderAdress {

    IDecoder getDecoder();

    void setDecoder(IDecoder decoder);

    AdressTyp getTyp();

    void setTyp(AdressTyp typ);

    Integer getAdress();

    void setAdress(Integer poles);
}