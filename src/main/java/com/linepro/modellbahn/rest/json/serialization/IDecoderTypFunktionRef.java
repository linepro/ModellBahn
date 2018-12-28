package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IDecoderTypFunktionRef extends ILinkRef {

    @JsonGetter(ApiNames.REIHE)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    String getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    String getBezeichnung();

    @JsonGetter(ApiNames.PROGRAMMABLE)
    Boolean getProgrammable();
}
