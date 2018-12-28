package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IDecoderAdressRef extends ILinkRef {

    @JsonGetter(ApiNames.ADRESS_TYP)
    AdressTyp getAdressTyp();

    @JsonGetter(ApiNames.ADRESS)
    String getAdress();
}
