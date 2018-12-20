package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

public interface ILink {
    @JsonGetter(ApiNames.HREF)
    String getHRef();

    @JsonGetter(ApiNames.REL)
    String getRel();

    @JsonGetter(ApiNames.METHOD)
    String getMethod();
}
