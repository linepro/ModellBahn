package com.linepro.modellbahn.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonRootName(value = ApiNames.FUNKTION)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ ApiNames.REIHE, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED })
@Schema(name = ApiNames.FUNKTION, description = "Decoder type function mapping - template for Decoder.")
public class DecoderTypFunktionModel extends RepresentationModel<DecoderTypFunktionModel> implements ItemModel {

    private static final long serialVersionUID = -4632521396017459814L;

    @JsonProperty(ApiNames.REIHE)

    @Schema(name = "Bank number (0-1) always 0 for single panel decoders", example = "0", required = true)
    private Integer reihe;

    @JsonProperty(ApiNames.FUNKTION)

    @Schema(name = "Function Key", example = "F0", required = true)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.PROGRAMMABLE)

    @Schema(name = "True if this function can be reassigned", example = "false", required = true)
    private Boolean programmable;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}
