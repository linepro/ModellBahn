package com.linepro.modellbahn.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonPropertyOrder({ApiNames.INDEX,  ApiNames.ADRESS_TYP,  ApiNames.SPAN,  ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED})
@Schema(name = ApiNames.ADRESS, description = "Decoder type address - template for Decoder.")
public class DecoderTypAdressModel extends RepresentationModel<DecoderTypAdressModel> implements ItemModel {

    private static final long serialVersionUID = 1826497356359114726L;

    @JsonProperty(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    @Schema(name = "0 based address index (always 0 for single address decoders)", example = "0", required = true)
    private Integer index;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Description", example = "First Motorola Address")
    private String bezeichnung;

    @JsonProperty(ApiNames.SPAN)
    @Schema(name = "Number of addresses consumed (1-16)", example = "1", required = true)
    private Integer span;

    @JsonProperty(ApiNames.ADRESS_TYP)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Address type", required = true)
    private AdressTyp adressTyp;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Default digital address", example = "80", required = true)
    private Integer werkeinstellung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}