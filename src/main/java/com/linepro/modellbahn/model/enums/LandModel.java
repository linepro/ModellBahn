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
@JsonRootName(value = ApiNames.LAND)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
                ApiNames.NAMEN, ApiNames.BEZEICHNUNG
})
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.LAND)
@Schema(name = ApiNames.LAND, description = "Countries")
public class LandModel extends DescribedEnumModel {

    public LandModel(String iso3Country, String displayCountry) {
        super(iso3Country, displayCountry, null);
    }

    @Override
    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "ISO3 Land code", example = "USA", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Land name", example = "Amerkia.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return super.getBezeichnung();
    }

    @Override
    @JsonProperty(ApiNames.TOOLTIP)
    @Schema(description = "Land tooltip", example = ".", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return super.getTooltip();
    }

    @Schema(name = ApiNames.LAND + "Page")
    public static class PagedLandModel extends PagedSchema<LandModel>{};
}
