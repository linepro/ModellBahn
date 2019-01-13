package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LINKS })
public interface INamedItemRef extends ILinkRef {
    String getName();

    String getBezeichnung();
}
