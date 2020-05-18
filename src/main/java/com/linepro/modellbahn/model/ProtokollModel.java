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
 * IProtokoll.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.PROTOKOLL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.PROTOKOLL, description = "Digital protocoll.")
public class ProtokollModel extends RepresentationModel<ProtokollModel> implements NamedItemModel {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Protocoll code", example = "MFX", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Protocoll description", example = "mfx")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}