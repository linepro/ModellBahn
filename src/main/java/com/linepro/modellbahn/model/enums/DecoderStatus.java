package com.linepro.modellbahn.model.enums;

/**
 * Stecker. Enumeration of Decoder connections
 * 
 * @author $Author:$
 * @version $Id:$
 */
public enum DecoderStatus implements DescribedEnum {

    INSTALIERT("instaliert"),

    FREI("frei"),

    BASTLER("bastler");

    private final String description;

    private final String tooltip;

    DecoderStatus(String description) {
        this.description = "{com.linepro.modellbahn.decoder." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.decoder." + description + ".tooltip}";
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
