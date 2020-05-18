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
 * Sondermodell.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.SONDERMODELL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.SONDERMODELL, description = "Special model - e.g,. MHI &c.")
public class SondermodellModel extends RepresentationModel<SondermodellModel> implements NamedItemModel {

    private static final long serialVersionUID = 5454366267511451526L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Sondermodell code", example = "MHI", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Sondermodell description", example = "Märklin Handler Initiative")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}