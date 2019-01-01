package com.linepro.modellbahn.model;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.URLSerializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IHersteller.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.HERSTELLER)
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.TELEFON, ApiNames.URL, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.HERSTELLER, description = "Manufacturer.")
public interface IHersteller extends INamedItem<NameKey> {

    @JsonGetter(ApiNames.URL)
    @JsonView(Views.Public.class)
    @JsonSerialize(using= URLSerializer.class)
    @ApiModelProperty(dataType = "String", value = "")
    URL getUrl();

    @JsonSetter(ApiNames.URL)
    void setUrl(URL url);

    @JsonGetter(ApiNames.TELEFON)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "")
    String getTelefon();

    @JsonSetter(ApiNames.TELEFON)
    void setTelefon(String telefon);
}