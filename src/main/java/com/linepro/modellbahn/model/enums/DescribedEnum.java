package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.linepro.modellbahn.controller.impl.ApiNames;

public interface DescribedEnum {

    @JsonProperty(ApiNames.NAMEN)
    String getName();

    @JsonProperty(ApiNames.BEZEICHNUNG)
    String getBezeichnung();

    @JsonProperty(ApiNames.TOOLTIP)
    String getTooltip();
}
