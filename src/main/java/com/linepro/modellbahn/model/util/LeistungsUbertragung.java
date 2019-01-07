package com.linepro.modellbahn.model.util;

import com.linepro.modellbahn.model.refs.IDescribedEnum;

/**
 * Konfiguration.
 * Enum defining the supported Decoder configuration methods
 * @author   $Author$
 * @version  $Id$
 */
public enum LeistungsUbertragung implements IDescribedEnum {

    NONE(""),
    
    ELEKTRISH("Elektrish"),

    HYDRAULISH("Hydraulish"),

    MECHANISH("Mechanish"),

    PRESSLUFT("Pressluft")    ;

    private final String description;
    
    LeistungsUbertragung(String description) {
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
