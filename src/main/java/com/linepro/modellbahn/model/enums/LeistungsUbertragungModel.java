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
@JsonRootName(value = ApiNames.LEISTUNGSUBERTRAGUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.LEISTUNGSUBERTRAGUNG)
@Schema(name = ApiNames.LEISTUNGSUBERTRAGUNG, description = "Drive mechanisms")
public class LeistungsUbertragungModel extends DescribedEnumModel {

    public LeistungsUbertragungModel(String name, String bezeichnung, String tooltip) {
        super(name, bezeichnung, tooltip);
    }

    @Override
    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Drive mechanism name", example = "ELEKTRISH", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Drive mechanism description", example = "Elektrish", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return super.getBezeichnung();
    }

    @Override
    @JsonProperty(ApiNames.TOOLTIP)
    @Schema(description = "Drive mechanism tooltip", example = "Elektrish", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return super.getTooltip();
    }

    @Schema(name = ApiNames.LEISTUNGSUBERTRAGUNG + "Page")
    public static class PagedLeistungsUbertragungModel extends PagedSchema<LeistungsUbertragungModel>{};
}
