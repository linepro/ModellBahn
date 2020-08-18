package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

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
        this.description = "${com.linepro.modellbahn.leistungsUbertragung." + description + ".bezeichnung}";
        this.tooltip = "${com.linepro.modellbahn.leistungsUbertragung." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "LeistungsUbertragung name", example = "ELEKTRISH", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "LeistungsUbertragung description", example = "Elektrish", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
