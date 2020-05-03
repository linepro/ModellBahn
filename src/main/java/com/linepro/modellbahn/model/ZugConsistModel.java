package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
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
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CONSIST)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.ZUG, ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.DELETED })
@ApiModel(value = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public class ZugConsistModel extends ItemModelImpl<ZugConsistModel> {

    private static final long serialVersionUID = -400353674903033600L;


    @JsonProperty(ApiNames.ZUG)
    @JsonView(Views.DropDown.class)
    
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ZugModel", value = "Parent train", access = "READ_ONLY", required = true)
    private ZugModel zug;

    
    @JsonProperty(ApiNames.POSITION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Contiguous 1 based position in the train (1 = head)", example = "1", access = "READ_ONLY", required = true)
    private Integer position;

    @JsonProperty(ApiNames.ARTIKEL)
    
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ArtikelModelRef", value = "Rolling stock item", access = "READ_ONLY", required = true)
    private ArtikelModel artikel;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}