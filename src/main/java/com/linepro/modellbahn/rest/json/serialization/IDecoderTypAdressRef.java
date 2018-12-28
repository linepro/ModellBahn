package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IDecoderTypAdressRef {

    @JsonGetter(ApiNames.INDEX)
    Integer getIndex();

    @JsonGetter(ApiNames.ADRESS_TYP)
    AdressTyp getAdressTyp();

    @JsonGetter(ApiNames.SPAN)
    Integer getSpan();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    Integer getWerkseinstellung();
}
