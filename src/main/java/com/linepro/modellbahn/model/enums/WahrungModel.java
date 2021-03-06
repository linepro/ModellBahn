package com.linepro.modellbahn.model.enums;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonRootName(value = ApiNames.WAHRUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.WAHRUNG)
@Schema(name = ApiNames.WAHRUNG, description = "Currencies")
public class WahrungModel extends DescribedEnumModel {

    private Integer decimals;

    private String symbol;

    public WahrungModel(String currencyCode, String displayName, String toolTip, String symbol, Integer decimals) {
        super(currencyCode, displayName, toolTip);

        this.decimals = decimals;
        this.symbol = symbol;
    }

    @Override
    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "ISO3 Wahrung code", example = "GBP", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Wahrung name", example = "Dollar", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return super.getBezeichnung();
    }

    @Override
    @JsonProperty(ApiNames.TOOLTIP)
    @Schema(description = "Wahrung tooltip", example = "$", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return super.getTooltip();
    }

    @JsonProperty(ApiNames.DEZIMAL)
    @Schema(description = "Wahrung decimals", example = "2", accessMode = AccessMode.READ_ONLY)
    public Integer getDecimals() {
        return decimals;
    }

    @JsonProperty(ApiNames.SYMBOL)
    @Schema(description = "Wahrung symbol", example = "$", accessMode = AccessMode.READ_ONLY)
    public String getSymbol() {
        return symbol;
    }

    @Schema(name = ApiNames.WAHRUNG + "Page")
    public static class PagedWahrungModel extends PagedSchema<WahrungModel>{};

}
