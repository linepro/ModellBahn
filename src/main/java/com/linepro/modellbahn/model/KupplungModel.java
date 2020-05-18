package com.linepro.modellbahn.model;

import java.io.Serializable;
import java.nio.file.Path;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IKupplung.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.KUPPLUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED })
@Schema(name = ApiNames.KUPPLUNG, description = "Coupling configuration - Märklin coding.")
public class KupplungModel extends RepresentationModel<KupplungModel> implements NamedItemModel, NamedWithAbbildung, Serializable {

    private static final long serialVersionUID = -4227031769285101775L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Kupplung code", example = "RELEX", required = true)
    private String name;
    
    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Kupplung description", example = "Relex")
    private String bezeichnung;

    @JsonProperty(ApiNames.ABBILDUNG)

    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private Path abbildung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}