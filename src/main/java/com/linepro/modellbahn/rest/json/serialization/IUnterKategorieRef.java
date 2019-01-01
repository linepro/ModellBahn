package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IUnterKategorieRef extends ILinkRef {

    @JsonGetter(ApiNames.KATEGORIE)
    @ApiModelProperty(value = "", required = true)
    String getKategorie();

    @JsonGetter(ApiNames.NAMEN)
    @ApiModelProperty(value = "", required = true)
    String getNamen();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    String getBezeichnung();
}
