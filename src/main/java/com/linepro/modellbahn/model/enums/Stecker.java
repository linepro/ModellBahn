package com.linepro.modellbahn.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;

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

    EINGEBAUT("Eingebaut", "Eingebaut / bedrahten"),

    NEM651("NEM 651", "MOROP 6 poliges stecker."),

    NEM652("NEM 652", "MOROP 8 poliges stecker."),

    NEM654("NEM 654", "MOROP 4 poliges stecker."),

    MTC21("MTC21", "Märklin 21 poliges stecker"),

    NEXT18("NextX18", "NEM 662 18 poliges stecker"),

    PLUX8("PluX8", "NEM 658 8 poliges stecker"),

    PLUX12("PluX12", "NEM 658 12 poliges stecker"),

    PLUX16("PluX16", "NEM 658 16 poliges stecker"),

    plux22("PluX22", "NEM 658 22 poliges stecker");

    private final String description;

    private final String tooltip;

    Stecker(String description, String tooltip) {
        this.description = description;
        this.tooltip = tooltip;
    }

    @Override
    @Schema(name = "Stecker type name", example = "EINGEBAUT", required = true)
    public String getName() {
        return this.name();
    }

    @Override
    @Schema(name = "Stecker type description", example = "Builin / Hardwired.", required = true)
    public String getBezeichnung() {
        return description;
    }

    @Override
    @Schema(name = "AdressTyp tooltip", example = "0 - 10239.")
    public String getTooltip() {
        return tooltip;
    }
}
