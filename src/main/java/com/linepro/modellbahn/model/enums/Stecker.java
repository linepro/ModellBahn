package com.linepro.modellbahn.model.enums;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
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
    public String getName() {
        return this.name();
    }

    @Override
    public String getBezeichnung() {
        return description;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }
}
