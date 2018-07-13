package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.impl.DecoderCVId;

public interface IDecoderCV {

    DecoderCVId getId();

    void setId(DecoderCVId id);

    Integer getWert();

    void setWert(Integer wert);

    int hashCode();

}