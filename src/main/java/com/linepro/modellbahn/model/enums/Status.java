package com.linepro.modellbahn.model.enums;

/**
 * Status.
 * The status of an article
 * @author   $Author$
 * @version  $Id$
 */
public enum Status implements DescribedEnum {
	
    WUNSCHMODEL("wunschmodel"),
	
    GEKAUFT("gekauft"),

    BASTELN("basteln"),
	
    VERKAUFT("verkauft");

    private final String description;

    private final String tooltip;

    Status(String description) {
        this.description = "{com.linepro.modellbahn.status." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.status." + description + ".tooltip}";
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