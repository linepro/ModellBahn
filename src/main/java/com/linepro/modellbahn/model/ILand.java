package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.resolver.WahrungResolver;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ILand.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.LAND)
@JsonPropertyOrder({ ApiNames.ID, ApiNames.WAHRUNG, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED,
        ApiNames.LINKS })
@ApiModel(value = ApiNames.LAND, description = "Country.")
public interface ILand extends INamedItem<NameKey> {

    /**
     * Gets the wahrung.
     *
     * @return the wahrung
     */
    @JsonGetter(ApiNames.WAHRUNG)
    @JsonView(Views.DropDown.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ApiNames.NAMEN, resolver= WahrungResolver.class)
    @ApiModelProperty(dataType = "String", value = "")
    IWahrung getWahrung();

    /**
     * Sets the wahrung.
     *
     * @param wharung the new wahrung
     */
    @JsonSetter(ApiNames.WAHRUNG)
    @JsonDeserialize(as= Wahrung.class)
    void setWahrung(IWahrung wharung);

}