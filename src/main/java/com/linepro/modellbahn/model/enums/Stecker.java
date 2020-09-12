package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
@JsonRootName(value = ApiNames.STECKER)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG })
@Schema(name = ApiNames.STECKER, description = "Stecker types")
public enum Stecker implements DescribedEnum {

    EINGEBAUT("eingebaut"),

    NEM651("nem651"),

    NEM652("nem652"),

    NEM654("nem654"),

    MTC21("mtc21"),

    NEXT18("nextx18"),

    PLUX8("plux8"),

    PLUX12("plux12"),

    PLUX16("plux16"),

    PLUX22("plux22"),
    
    VERDRAHTET("verdrahtet");
    
    private final String description;

    private final String tooltip;

    Stecker(String description) {
        this.description = "{com.linepro.modellbahn.stecker." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.stecker." + description + ".tooltip}";
    }

    @Override
    @Schema(description = "Stecker type name", example = "EINGEBAUT", accessMode = AccessMode.READ_ONLY)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(description = "Stecker type description", example = "Built in / Hardwired.", accessMode = AccessMode.READ_ONLY)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(description = "Stecker type tooltip", example = "Wired.", accessMode = AccessMode.READ_ONLY)
    public String getTooltip() {
        return tooltip;
    }
}
