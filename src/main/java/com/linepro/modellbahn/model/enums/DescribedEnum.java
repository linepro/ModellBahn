package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.rest.json.Views;

public interface DescribedEnum {

    @JsonProperty(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    String getName();

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    String getBezeichnung();

    @JsonProperty(ApiNames.TOOLTIP)
    @JsonView(Views.DropDown.class)
    String getTooltip();
}
