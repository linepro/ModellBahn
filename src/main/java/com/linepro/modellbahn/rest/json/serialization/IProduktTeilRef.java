package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public interface IProduktTeilRef {

    @JsonGetter(ApiNames.HERSTELLER)
    String getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    String getBestellNr();

    @JsonGetter(ApiNames.ANZAHL)
    Integer getAnzahl();

    @JsonGetter(ApiNames.LINKS)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(contentAs= ILink.class, contentUsing= LinkSerializer.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.rest.json.serialization.ILink;", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    Set<ILink> getLinks();
}
