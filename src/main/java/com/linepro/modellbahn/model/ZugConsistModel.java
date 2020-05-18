package com.linepro.modellbahn.model;

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
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CONSIST)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.DELETED })
@Schema(name = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public class ZugConsistModel extends RepresentationModel<ZugConsistModel> implements ItemModel {

    private static final long serialVersionUID = -400353674903033600L;

    @JsonProperty(ApiNames.ZUG)

    @Schema(name = "Train code", example = "BAVARIA", required = true)
    private String zug;

    @JsonProperty(ApiNames.POSITION)

    @Schema(name = "Contiguous 1 based position in the train (1 = head)", example = "1", accessMode = AccessMode.READ_ONLY, required = true)
    private Integer position;

    @JsonProperty(ApiNames.ARTIKEL)

    @Schema(implementation = ArtikelModel.class, name = "Rolling stock item", accessMode = AccessMode.READ_ONLY, required = true)
    private ArtikelModel artikel;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}