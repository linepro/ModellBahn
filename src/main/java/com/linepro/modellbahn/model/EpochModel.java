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
 * IEpoch.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.EPOCH)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED })
@Schema(name = ApiNames.EPOCH, description = "Era - NEM 800.")
public class EpochModel extends RepresentationModel<EpochModel> implements NamedItemModel {

    private static final long serialVersionUID = 2877272020386187147L;

    @JsonProperty(ApiNames.NAMEN)
    @Schema(name = "ERA", example = "III", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "ERA description", example = "III : 1949 - 1970")
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}