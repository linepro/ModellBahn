package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
public interface INamedItemRef extends IRef {
    String getName();

    String getBezeichnung();
}
