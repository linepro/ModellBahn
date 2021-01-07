package com.linepro.modellbahn.model.enums;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.STECKER)
@Schema(name = ApiNames.STECKER, description = "Stecker types")
public class SteckerModel extends DescribedEnumModel {

    public SteckerModel(String name, String bezeichnung, String tooltip) {
        super(name, bezeichnung, tooltip);
    }

    @Override
    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Stecker type name", example = "EINGEBAUT", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Stecker type description", example = "Built in / Hardwired.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return super.getBezeichnung();
    }

    @Override
    @JsonProperty(ApiNames.TOOLTIP)
    @Schema(description = "Stecker type tooltip", example = "Wired.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return super.getTooltip();
    }

    @Schema(name = ApiNames.ADRESS_TYP + "Page")
    public static class PagedSteckerModel extends PagedSchema<SteckerModel>{};
}
