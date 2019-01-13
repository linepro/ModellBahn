package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
@JsonPropertyOrder({ ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.LINKS })
@ApiModel(value = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public interface IZugConsistRef extends ILinkRef {

    @JsonGetter(ApiNames.POSITION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Contiguous 1 based position in the train (1 = head)", example = "1", accessMode = AccessMode.READ_ONLY, required = true)
    Integer getPosition();

    @JsonGetter(ApiNames.ARTIKEL)
    @JsonSerialize(contentAs = IArtikelRef.class)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IProduktRef", value = "Rolling stock item", accessMode = AccessMode.READ_ONLY, required = true)
    IArtikel getArtikel();
}