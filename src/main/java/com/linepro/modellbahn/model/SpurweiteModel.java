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
 * ISpurweite.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.SPURWEITE)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.SPURWEITE, description = "Track gauge - NEM 010.")
public class SpurweiteModel extends RepresentationModel<SpurweiteModel> implements NamedItemModel {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Spurweite code", example = "H0", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Spurweite description", example = "H0 : 16,5mm")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}