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
 * IAchsfolg.
 * Represents an axle configuration (German nomenclature)
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ACHSFOLG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.ACHSFOLG, description = "Axle configuration - VDEV/VMEV/UIC-System")
public class AchsfolgModel extends RepresentationModel<AchsfolgModel> implements NamedItemModel {

    private static final long serialVersionUID = 1860366231843697997L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "UIC Code", example = "1E1H3T", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Description", example = "1′E1′ h3t")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}