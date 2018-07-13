package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.impl.DecoderFunktionId;

public interface IDecoderFunktion {

    DecoderFunktionId getId();

    void setId(DecoderFunktionId id);

    String getWert();

    void setWert(String wert);

}
