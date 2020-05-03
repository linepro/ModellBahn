package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
import com.linepro.modellbahn.model.enums.AdressTyp;
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
 * IDecoderTypAdress.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ADRESS)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ApiNames.DECODER_TYP,  ApiNames.INDEX,  ApiNames.ADRESS_TYP,  ApiNames.SPAN,  ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED})
@ApiModel(value = ApiNames.ADRESS, description = "Decoder type address - template for Decoder.")
public class DecoderTypAdressModel extends ItemModelImpl<DecoderTypAdressModel> {

    private static final long serialVersionUID = 1826497356359114726L;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderTypModel", value = "Decoder type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.INDEX)
    @ApiModelProperty(value = "0 based address index (always 0 for single address decoders)", example = "0", required = true)
    private Integer index;

    @JsonProperty(ApiNames.SPAN)
    @ApiModelProperty(value = "Number of addresses consumed (1-16)", example = "1", required = true)
    private Integer span;

    @JsonProperty(ApiNames.ADRESS_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Address type", required = true)
    private AdressTyp adressTyp;

    @JsonProperty(ApiNames.ADRESS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Digital address", example = "80", required = true)
    private Integer adress;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}