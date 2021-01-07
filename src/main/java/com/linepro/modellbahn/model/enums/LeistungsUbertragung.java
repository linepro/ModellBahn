package com.linepro.modellbahn.model.enums;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
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
