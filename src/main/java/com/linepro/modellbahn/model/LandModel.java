package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.NamedItemModelImpl;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ILand.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.LAND)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.WAHRUNG, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@ApiModel(value = ApiNames.LAND, description = "Country.")
public class LandModel extends NamedItemModelImpl<LandModel> {

    private static final long serialVersionUID = -6068597861407215908L;

    @JsonProperty(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "ISO Country code", example = "UK", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Country Name", example = "Vereinigtes K�nigreich")
    private String bezeichnung;

    @JsonProperty(ApiNames.WAHRUNG)
    @JsonView(Views.DropDown.class)
    
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.WahrungModel", value = "Currency", required = true)
    private WahrungModel wahrung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}