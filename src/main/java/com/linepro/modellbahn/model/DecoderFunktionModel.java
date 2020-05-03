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
 * IDecoderFunktion.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.FUNKTION)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.DECODER, ApiNames.FUNKTION,  ApiNames.BEZEICHNUNG, ApiNames.DELETED})
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder function mapping.")
public class DecoderFunktionModel extends ItemModelImpl<DecoderFunktionModel> {

    private static final long serialVersionUID = -5315298133158215960L;

    @JsonProperty(ApiNames.DECODER)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderModel", value = "Parent decoder", required = true)
    private DecoderModel decoder;

    @JsonProperty(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Reihe - Bank Number", example = "0", required = true)
    private Integer reihe;

    @JsonProperty(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Function Key", example = "F0", required = true)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
