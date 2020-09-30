package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.LEISTUNGSUBERTRAGUNG)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.LEISTUNGSUBERTRAGUNG, description = "Drive mechanisms")
public enum LeistungsUbertragung implements DescribedEnum {

    KEIN("kein"),

    ELEKTRISH("elektrish"),

    HYDRAULISH("hydraulish"),

    MECHANISH("mechanish"),

    PRESSLUFT("pressluft");

    private final String description;

    private final String tooltip;

    LeistungsUbertragung(String description) {
        this.description = "{com.linepro.modellbahn.leistungsUbertragung." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.leistungsUbertragung." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "Drive mechanism name", example = "ELEKTRISH", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Drive mechanism description", example = "Elektrish", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Drive mechanism tooltip", example = "Elektrish", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return tooltip;
    }
}
