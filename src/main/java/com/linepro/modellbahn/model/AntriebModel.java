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
 * IAchsfolg.
 * Represents an axle configuration (German nomenclature)
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ANTRIEB)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@ApiModel(value = ApiNames.ANTRIEB, description = "Drive method")
public class AntriebModel extends NamedItemModelImpl<AntriebModel> {

    private static final long serialVersionUID = 5645402222171634730L;

    @JsonProperty(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Drive code", example = "DAMPF", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Drive description", example = "Dampf")
    public String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}