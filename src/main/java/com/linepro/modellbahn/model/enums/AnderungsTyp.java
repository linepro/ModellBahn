package com.linepro.modellbahn.model.enums;

/**
 * ChangeTyp.
 * Enum defining the supported address types.
 * @author   $Author$
 * @version  $Id$
 */
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
        this.description = "{com.linepro.modellbahn.anderungsTyp." + description + ".bezeichnung}";
        this.tooltip = "{com.linepro.modellbahn.anderungsTyp." + description + ".tooltip}";
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
