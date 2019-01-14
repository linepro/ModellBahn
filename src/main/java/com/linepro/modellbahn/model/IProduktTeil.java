package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.keys.ProduktTeilKey;
import com.linepro.modellbahn.model.refs.IProduktRef;
import com.linepro.modellbahn.model.refs.IProduktTeilRef;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.ProduktDeserializer;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * IProduktTeil.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.TEIL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.ID, ApiNames.PRODUKT,ApiNames.TEIL, ApiNames.ANZAHL, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public interface IProduktTeil extends IItem<ProduktTeilKey>, IProduktTeilRef {

    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProduktRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IProduktRef", value = "Head product", accessMode = AccessMode.READ_ONLY, required = true)
    IProdukt getProdukt();

    @JsonSetter(ApiNames.PRODUKT)
    @JsonDeserialize(using= ProduktDeserializer.class)
    void setProdukt(IProdukt produkt);

    @JsonSetter(ApiNames.TEIL)
    @JsonDeserialize(using= ProduktDeserializer.class)
    void setTeil(IProdukt teil);

    @JsonSetter(ApiNames.ANZAHL)
    void setAnzahl(Integer anzahl);
}