package com.linepro.modellbahn.model.enums;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
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
