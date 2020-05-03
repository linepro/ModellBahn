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
 * IDecoderTypCV.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CV)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.DECODER_TYP, ApiNames.CV, ApiNames.BEZEICHNUNG, ApiNames.MINIMAL, ApiNames.MAXIMAL,
        ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED})
@ApiModel(value = ApiNames.CV, description = "Decoder type CV - template for Decoder.")
public class DecoderTypCvModel extends ItemModelImpl<DecoderTypCvModel> {

    private static final long serialVersionUID = -8055283388490564394L;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderTypModel", value = "Decoder type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "CV number", example = "63", required = true)
    private Integer cv;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "CV usage", example = "Ger�uschlautst�rke", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.MINIMAL)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Minimum value", example = "1")
    private Integer minimal;

    @JsonProperty(ApiNames.MAXIMAL)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Maximum value", example = "63")
    private Integer maximal;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Default value", example = "63", required = true)
    private Integer werkseinstellung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
