package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * ChangeTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.ANDERUNGS_TYP)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.LINKS })
@Schema(name = ApiNames.ANDERUNGS_TYP, description = "Change types")
public enum AnderungsTyp implements DescribedEnum {

    AUSGETAUSCHT("ausgetauscht"),

    ERSATZTEILE("ersatzteile"),

    REPARIERT("repariert"), 

    UMGEBAUT("umgebaut"),

    VERKAUFT("verkauft"),

    VERLOREN("verloren"),

    VERSCHROTTET("verschrottet");

    private final String description;

    private final String tooltip;

    AnderungsTyp(String description) {
        this.description = "com.linepro.modellbahn.anderungsTyp." + description + ".bezeichnung";
        this.tooltip = "com.linepro.modellbahn.anderungsTyp." + description + ".tooltip";
    }

    @Override
    @Schema(description = "Change type", example = "UMGEBAUT", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Change type description", example = "umgebaut", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Change type tooltip")
    public String getTooltip() {
        return tooltip;
    }
}
