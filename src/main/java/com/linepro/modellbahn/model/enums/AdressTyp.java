package com.linepro.modellbahn.model.enums;

/**
 * AdressTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
public enum AdressTyp implements DescribedEnum {

    DCC("dcc"),

    DCC_SHORT("kurz"),

    DELTA("delta"),

    MOTOROLA("motorola"),

    DIGITAL("digital"),

    MAGNETARTIKEL("magnetartikel");

    private final String description;

    private final String tooltip;

    AdressTyp(String description) {
        this.description = "{com.linepro.modellbahn.adressTyp." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.adressTyp." + description + ".tooltip}";
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
