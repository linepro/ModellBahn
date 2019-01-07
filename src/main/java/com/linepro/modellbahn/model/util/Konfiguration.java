package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.refs.IDescribedEnum;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
public enum Konfiguration implements IDescribedEnum {

    NONE("The Decoder cannot be configured."),

    LINK("The Decoder can be configured by link (solder or jumper)."),

    SWITCH("The Decoder can be configured by switches."),

    CV("The Decoder can be configured by CV values.");

    private final String description;
    
    Konfiguration(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getBezeichnung() {
        return description;
    }
}
