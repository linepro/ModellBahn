package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface IDecoderCVRef extends ILinkRef {

    @JsonGetter(ApiNames.CV)
    Integer getCv();

    @JsonGetter(ApiNames.WERT)
    Integer getWert();
}
