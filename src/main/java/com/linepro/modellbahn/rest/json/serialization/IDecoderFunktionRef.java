package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IDecoderFunktionRef extends ILinkRef {

    @JsonGetter(ApiNames.REIHE)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    String getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    String getBezeichnung();
}
