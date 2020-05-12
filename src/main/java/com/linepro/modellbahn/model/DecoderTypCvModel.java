package com.linepro.modellbahn.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonPropertyOrder({ ApiNames.CV, ApiNames.BEZEICHNUNG, ApiNames.MINIMAL, ApiNames.MAXIMAL, ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED })
@Schema(name = ApiNames.CV, description = "Decoder type CV - template for Decoder.")
public class DecoderTypCvModel extends RepresentationModel<DecoderTypCvModel> implements ItemModel {

    private static final long serialVersionUID = -8055283388490564394L;

    @JsonProperty(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @Schema(name = "CV number", example = "63", required = true)
    private Integer cv;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "CV usage", example = "Ger�uschlautst�rke", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.MINIMAL)
    @JsonView(Views.Public.class)
    @Schema(name = "Minimum value", example = "1")
    private Integer minimal;

    @JsonProperty(ApiNames.MAXIMAL)
    @JsonView(Views.Public.class)
    @Schema(name = "Maximum value", example = "63")
    private Integer maximal;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @JsonView(Views.Public.class)
    @Schema(name = "Default value", example = "63", required = true)
    private Integer werkseinstellung;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
