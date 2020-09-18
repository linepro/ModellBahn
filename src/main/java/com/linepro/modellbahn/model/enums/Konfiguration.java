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
@JsonRootName(value = ApiNames.KONFIGURATION)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.KONFIGURATION, description = "Configuration methods")
public enum Konfiguration implements DescribedEnum {

    KEIN("kein"),

    STECKER("stecker"),

    SCHALTER("schalter"),

    CV("cv");

    private final String description;

    private final String tooltip;

    Konfiguration(String description) {
        this.description = "{com.linepro.modellbahn.konfiguration." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.konfiguration." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "Konfiguration name", example = "CV", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Konfiguration description", example = "The Decoder can be configured by CV values.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Konfiguration tooltip", example = "The Decoder can be configured by CV values.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return tooltip;
    }
}
