package com.linepro.modellbahn.model;

import java.util.List;

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
 * IZug.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ZUG)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.ZUG_TYP, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DELETED,
        ApiNames.CONSIST})
@Schema(name = ApiNames.ZUG, description = "A running train configuration.")
public class ZugModel extends RepresentationModel<ZugModel> implements NamedItemModel {

    private static final long serialVersionUID = -3702381278455257877L;

    @JsonProperty(ApiNames.NAMEN)

    @Schema(name = "Train code", example = "BAVARIA", required = true)
    private String name;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Train description", example = "TEE „Bavaria“")
    private String bezeichnung;
    @JsonProperty(ApiNames.ZUG_TYP)
    
    @Schema(implementation = ZugTypModel.class, name = "Train type", required = true)
    private ZugTypModel zugTyp;

    @JsonProperty(ApiNames.CONSIST)

    @Schema(implementation = ZugConsistModel.class, name = "Train composition", accessMode = AccessMode.READ_ONLY)
    private List<ZugConsistModel> consist;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}
