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
 * ProduktDtoTeil.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.TEIL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.PRODUKT, ApiNames.TEIL, ApiNames.ANZAHL, ApiNames.DELETED})
@ApiModel(value = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public class ProduktTeilModel extends ItemModelImpl<ProduktTeilModel> {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ProduktDtoRef", value = "Head product", access = "READ_ONLY", required = true)
    private ProduktModel produkt;

    @JsonProperty(ApiNames.TEIL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ProduktDtoRef", value = "Sub product details (spare parts / set contents)", access = "READ_ONLY", required = true)
    private ProduktModel teil;

    @JsonProperty(ApiNames.ANZAHL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Number included", example = "1", required = true)
    private Integer anzahl;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
