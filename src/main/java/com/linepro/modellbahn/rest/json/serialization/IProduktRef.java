package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IProduktRef extends ILinkRef {

    @JsonGetter(ApiNames.HERSTELLER)
    String getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    String getBestellNr();
}
