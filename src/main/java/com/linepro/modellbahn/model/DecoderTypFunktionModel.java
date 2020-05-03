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
 * IDecoderTypFunktion.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(ApiNames.FUNKTION)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.DECODER_TYP, ApiNames.REIHE, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG,
        ApiNames.PROGRAMMABLE, ApiNames.DELETED})
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder type function mapping - template for Decoder.")
public class DecoderTypFunktionModel extends ItemModelImpl<DecoderTypFunktionModel> {

    private static final long serialVersionUID = -4632521396017459814L;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderTypModel", value = "Decoder type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Bank number (0-1) always 0 for single panel decoders", example = "0", required = true)
    private Integer reihe;

    @JsonProperty(ApiNames.FUNKTION)
    @ApiModelProperty(value = "Function number", example = "F0", required = true)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.PROGRAMMABLE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if this function can be reassigned", example = "false", required = true)
    private Boolean programmable;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}