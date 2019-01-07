package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public interface IZugConsistRef extends IRef {

    @JsonGetter(ApiNames.POSITION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", accessMode = AccessMode.READ_ONLY, required = true)
    Integer getPosition();

    @JsonGetter(ApiNames.ARTIKEL)
    @JsonSerialize(contentAs = IArtikelRef.class)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", accessMode = AccessMode.READ_ONLY, required = true)
    IArtikel getArtikel();
}